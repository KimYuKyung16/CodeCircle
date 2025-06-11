package com.algorithmsolutionproject.algorithmsolution.service;

import com.algorithmsolutionproject.algorithmsolution.dto.problem.ParsedProblemDTO;
import com.algorithmsolutionproject.algorithmsolution.dto.problem.ProblemDetailResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.problem.SubmissonsByProblemIdResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.problem.TestCaseDTO;
import com.algorithmsolutionproject.algorithmsolution.entity.Problem;
import com.algorithmsolutionproject.algorithmsolution.entity.Submission;
import com.algorithmsolutionproject.algorithmsolution.entity.TestCase;
import com.algorithmsolutionproject.algorithmsolution.repository.ProblemRepository;
import com.algorithmsolutionproject.algorithmsolution.repository.SubmissionRepository;
import com.algorithmsolutionproject.algorithmsolution.repository.TestCaseRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProblemService {
    private final ProblemRepository problemRepository;
    private final TestCaseRepository testCaseRepository;
    private final SubmissionRepository submissionRepository;
    @Value("${algorithm.site}")
    private String algorithmSite;
    @Value("${user.agent}")
    private String userAgent;

    // num을 이용한 문제 상세 조회
    @Transactional
    public ProblemDetailResponse getProblemDetailByNum(Integer num) {
        Problem problem = problemRepository.findByNum(num)
                .orElseGet(() -> {
                    log.info("DB에 해당하는 문제 데이터가 없습니다.");
                    ParsedProblemDTO parsedProblem = getProblemAndParse(num);
                    return saveProblemWithTestCases(parsedProblem);
                });
        List<TestCaseDTO> testCases = testCaseRepository.findByProblemId(problem.getId());

        return ProblemDetailResponse.from(problem, testCases);
    }

    // id를 이용한 문제 상세 조회
    @Transactional
    public ProblemDetailResponse getProblemDetailById(Integer problemId) {
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new EntityNotFoundException("해당 문제를 찾을 수 없습니다."));
        List<TestCaseDTO> testCases = testCaseRepository.findByProblemId(problem.getId());

        return ProblemDetailResponse.from(problem, testCases);
    }

    // 제출 내역 조회
    @Transactional
    public SubmissonsByProblemIdResponse getSubmissionsByProblemId(Integer userId, Integer problemId) {
        List<Submission> submissions = submissionRepository.findByUserIdAndProblemId(userId, problemId);
        return SubmissonsByProblemIdResponse.from(submissions);
    }

    private ParsedProblemDTO getProblemAndParse(Integer num) {
        String url = algorithmSite + "/problem/" + num;

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", userAgent);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                entity,
                String.class
        );

        String body = Optional.ofNullable(response.getBody())
                .orElseThrow(() -> new IllegalStateException("응답 본문이 비어 있습니다"));
        Document doc = Jsoup.parse(body);

        Element metaTag = doc.selectFirst("meta[name=problem-id]");
        Element titleTag = doc.selectFirst("#problem_title");
        Element descriptionTag = doc.selectFirst("#problem_description");
        if (metaTag == null) {
            throw new IllegalStateException("문제 ID 메타 태그를 찾을 수 없습니다.");
        }
        if (titleTag == null) {
            throw new IllegalStateException("문제 Title 태그를 찾을 수 없습니다.");
        }
        if (descriptionTag == null) {
            throw new IllegalStateException("문제 Title 태그를 찾을 수 없습니다.");
        }

        int problemId = Integer.parseInt(metaTag.attr("content"));
        String title = titleTag.text();
        String description = descriptionTag.text();

        // 모든 sample input
        List<String> sampleInputs = new ArrayList<>();
        Elements inputElements = doc.select("pre[id^=sample-input-]");
        for (Element input : inputElements) {
            sampleInputs.add(input.text());
        }
        // 모든 sample output
        List<String> sampleOutputs = new ArrayList<>();
        Elements outputElements = doc.select("pre[id^=sample-output-]");
        for (Element output : outputElements) {
            sampleOutputs.add(output.text());
        }

        return ParsedProblemDTO.builder()
                .num(problemId)
                .title(title)
                .description(description)
                .sampleInputs(sampleInputs)
                .sampleOutputs(sampleOutputs)
                .build();
    }

    private Problem saveProblemWithTestCases(ParsedProblemDTO parsedProblemDTO) {
        Problem problem = Problem.builder()
                .num(parsedProblemDTO.num())
                .title(parsedProblemDTO.title())
                .description(parsedProblemDTO.description())
                .build();
        Problem savedProblem = problemRepository.save(problem);

        List<String> sampleInputs = parsedProblemDTO.sampleInputs();
        List<String> sampleOutputs = parsedProblemDTO.sampleOutputs();
        for (int i = 0; i < sampleInputs.size(); i++) {
            String input = sampleInputs.get(i);
            String output = i < sampleOutputs.size() ? sampleOutputs.get(i) : "";

            TestCase testCase = TestCase.builder()
                    .input(input)
                    .expectedOutput(output)
                    .isSample(true)
                    .problem(problem)
                    .build();
            testCaseRepository.save(testCase);
        }

        return savedProblem;
    }
}
