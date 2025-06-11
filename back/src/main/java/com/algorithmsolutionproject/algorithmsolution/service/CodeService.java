package com.algorithmsolutionproject.algorithmsolution.service;

import com.algorithmsolutionproject.algorithmsolution.dto.problem.RunTestCaseDTO;
import com.algorithmsolutionproject.algorithmsolution.dto.problem.TestCaseDTO;
import com.algorithmsolutionproject.algorithmsolution.dto.room.ExecuteCodeAndStoreResultResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.SubmitCodeResponse;
import com.algorithmsolutionproject.algorithmsolution.entity.Problem;
import com.algorithmsolutionproject.algorithmsolution.entity.Room;
import com.algorithmsolutionproject.algorithmsolution.entity.Submission;
import com.algorithmsolutionproject.algorithmsolution.entity.User;
import com.algorithmsolutionproject.algorithmsolution.repository.ProblemRepository;
import com.algorithmsolutionproject.algorithmsolution.repository.RoomRepository;
import com.algorithmsolutionproject.algorithmsolution.repository.SubmissionRepository;
import com.algorithmsolutionproject.algorithmsolution.repository.TestCaseRepository;
import com.algorithmsolutionproject.algorithmsolution.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CodeService {
    private final RoomRepository roomRepository;
    private final TestCaseRepository testCaseRepository;
    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;
    private final SubmissionRepository submissionRepository;

    // docker를 이용해서 코드 실행
    @Transactional
    public ExecuteCodeAndStoreResultResponse executeCode(Integer roomId, Integer problemId, String code) {
        String language = getLanguageByRoomId(roomId);
        List<TestCaseDTO> testcases = getTestCases(problemId);
        List<RunTestCaseDTO> results = runTestCases(code, language, testcases);
        log.info("최종 결과 = {}", results);
        return ExecuteCodeAndStoreResultResponse.from(results);
    }

    // 코드 제출
    @Transactional
    public SubmitCodeResponse submitCode(Integer userId, Integer roomId, Integer problemId, String code) {
        Submission submission = saveSubmission(userId, roomId, problemId, code);
        return new SubmitCodeResponse(submission.getId());
    }

    public Submission saveSubmission(Integer userId, Integer roomId, Integer problemId, String code) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("문제를 찾을 수 없습니다."));
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("방을 찾을 수 없습니다."));

        Submission submission = Submission.builder()
                .user(user)
                .problem(problem)
                .room(room)
                .code(code)
                .language(room.getLanguage())
                .build();

        return submissionRepository.save(submission);
    }

    private String getLanguageByRoomId(Integer roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("해당 방이 없습니다."))
                .getLanguage();
    }

    private List<TestCaseDTO> getTestCases(Integer problemId) {
        List<TestCaseDTO> testcases = testCaseRepository.findByProblemId(problemId);
        if (testcases.isEmpty()) {
            throw new EntityNotFoundException("테스트케이스가 없습니다.");
        }
        return testcases;
    }

    private List<RunTestCaseDTO> runTestCases(String code, String language, List<TestCaseDTO> testcases) {
        List<RunTestCaseDTO> results = new ArrayList<>();
        for (TestCaseDTO testcase : testcases) {
            results.add(runSingleTestCase(code, language, testcase));
        }
        return results;
    }

    private RunTestCaseDTO runSingleTestCase(String code, String language, TestCaseDTO testcase) {
        String input = testcase.input();
        String expectedOutput = testcase.expectedOutput();
        String output;
        String result;

        try {
            output = runCode(code, input, language);

            if (output != null && output.strip().equals(expectedOutput.strip())) {
                result = "성공";
            } else if ("시간 초과".equals(output)) {
                result = "시간 초과";
            } else {
                result = "실패";
            }
        } catch (Exception e) {
            output = "";
            result = "실행 오류: " + e.getMessage();
            log.warn("❌ 실행 오류: {}", e.getMessage());
        }

        return RunTestCaseDTO.builder()
                .input(input)
                .expectedOutput(expectedOutput)
                .output(output)
                .result(result)
                .build();
    }

    // docker 코드 실행
    private String runCode(String code, String input,
                           String language) throws IOException, InterruptedException {
        Path tempDir = Files.createTempDirectory("code");
        String fileName;
        String imageName;

        switch (language.toLowerCase()) {
            case "java" -> {
                fileName = "Main.java";
                imageName = "java-runner-image";
            }
            case "python" -> {
                fileName = "main.py";
                imageName = "python-runner-image";
            }
            case "cpp" -> {
                fileName = "main.cpp";
                imageName = "cpp-runner-image";
            }
            default -> throw new IllegalArgumentException("지원하지 않는 언어입니다: " + language);
        }

        Files.writeString(tempDir.resolve(fileName), code);
        Files.writeString(tempDir.resolve("input.txt"), input);

        Path runScriptPath = tempDir.resolve("run.sh");
        Path runScriptSource = Path.of(System.getProperty("user.dir"), "docker", language + "-runner", "run.sh");
        Files.copy(runScriptSource, runScriptPath);
        File file = runScriptPath.toFile();
        if (!file.setExecutable(true)) {
            throw new RuntimeException("실행 권한을 설정하는 데 실패했습니다: " + file.getAbsolutePath());
        }

        Files.writeString(runScriptPath,
                Files.readString(runScriptPath).replace("\r\n", "\n"));

        // Docker 명령어 실행
        ProcessBuilder pb = new ProcessBuilder(
                "docker", "run", "--rm",
                "-v", tempDir.toAbsolutePath() + ":/app",
                "--memory", "256m", "--cpus", "0.5",
                imageName
        );
        pb.redirectErrorStream(true);
        Process process = pb.start();

        boolean finished = process.waitFor(10, TimeUnit.SECONDS);
        int exitCode = finished ? process.exitValue() : -1;

        if (!finished) {
            log.warn("시간 초과: 10초 내에 코드 실행이 끝나지 않았습니다.");
            return "시간 초과";
        }

        StringBuilder dockerLogs = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dockerLogs.append(line).append("\n");
            }
        }

        log.info("Docker 로그\n{}", dockerLogs);
        log.info("종료 코드: {}", exitCode);

        Path outputPath = tempDir.resolve("output.txt");
        Path timePath = tempDir.resolve("time.txt");

        String output = Files.exists(outputPath) ? Files.readString(outputPath) : "출력이 없습니다.";
        String timeLog = Files.exists(timePath) ? Files.readString(timePath) : "시간 정보가 없습니다.";

        log.info("실행 결과:\n{}", output);
        log.info("시간/에러 로그:\n{}", timeLog);

        if (exitCode != 0) {
            if (timeLog != null && timeLog.contains("Exception in thread")) {
                int lineIdx = timeLog.indexOf("Exception in thread");
                String errorSummary = timeLog.substring(lineIdx).split("\n")[0];
                throw new RuntimeException(errorSummary);
            }
            return "실행 오류";
        }

        return output;
    }
}
