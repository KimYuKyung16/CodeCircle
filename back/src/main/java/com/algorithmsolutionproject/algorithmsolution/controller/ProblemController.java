package com.algorithmsolutionproject.algorithmsolution.controller;

import com.algorithmsolutionproject.algorithmsolution.dto.common.ApiResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.problem.ProblemDetailResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.problem.SubmissonsByProblemIdResponse;
import com.algorithmsolutionproject.algorithmsolution.security.CustomUserPrincipal;
import com.algorithmsolutionproject.algorithmsolution.service.ProblemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/problems")
public class ProblemController {
    private final ProblemService problemService;

    // 문제 상세 조회 (num 이용) - db에 없는 경우도 고려
    @GetMapping("/num/{problemNum}")
    public ResponseEntity<ApiResponse<ProblemDetailResponse>> getProblemDetailByNum(
            @PathVariable("problemNum") Integer problemNum) {
        ProblemDetailResponse response = problemService.getProblemDetailByNum(problemNum);
        log.info("문제 조회 결과 = {}", response);
        return ResponseEntity.ok(ApiResponse.success("문제를 성공적으로 조회했습니다", response));
    }

    // 문제 상세 조회 (id 이용)
    @GetMapping("/id/{problemId}")
    public ResponseEntity<ApiResponse<ProblemDetailResponse>> getProblemDetailById(
            @PathVariable("problemId") Integer problemId) {
        ProblemDetailResponse response = problemService.getProblemDetailById(problemId);
        log.info("문제 조회 결과 = {}", response);
        return ResponseEntity.ok(ApiResponse.success("문제를 성공적으로 조회했습니다", response));
    }

    // 제출 내역 조회
    @GetMapping("/{problemId}/submissions")
    public ResponseEntity<ApiResponse<SubmissonsByProblemIdResponse>> getSubmissionsByProblemId(
            Authentication authentication,
            @PathVariable("problemId") Integer problemId) {
        System.out.println("제출 내역 조회");
        CustomUserPrincipal principal = (CustomUserPrincipal) authentication.getPrincipal();
        int userId = principal.userId();
        SubmissonsByProblemIdResponse response = problemService.getSubmissionsByProblemId(userId, problemId);
        return ResponseEntity.ok(ApiResponse.success("제출 내역을 성공적으로 조회했습니다", response));
    }
}
