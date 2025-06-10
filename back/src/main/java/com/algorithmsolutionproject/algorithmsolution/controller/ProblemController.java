package com.algorithmsolutionproject.algorithmsolution.controller;

import com.algorithmsolutionproject.algorithmsolution.dto.common.ApiResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.problem.ProblemDetailResponse;
import com.algorithmsolutionproject.algorithmsolution.service.ProblemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/problem")
public class ProblemController {
    private final ProblemService problemService;

    // 문제 상세 조회 (num 이용) - db에 없는 경우도 고려
    @GetMapping("/{problemNum}")
    public ResponseEntity<ApiResponse<ProblemDetailResponse>> getProblemDetailByNum(
            @PathVariable("problemNum") Integer problemNum) {
        ProblemDetailResponse response = problemService.getProblemDetailByNum(problemNum);
        log.info("문제 조회 결과 = {}", response);
        return ResponseEntity.ok(ApiResponse.success("문제를 성공적으로 조회했습니다", response));
    }
}
