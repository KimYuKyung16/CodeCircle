package com.algorithmsolutionproject.algorithmsolution.dto.problem;

import com.algorithmsolutionproject.algorithmsolution.entity.Problem;
import java.util.List;
import lombok.Builder;

@Builder
public record ProblemDetailResponse(
        int id,
        int num,
        String title,
        String description,
        List<TestCaseDTO> testCases
) {
    public static ProblemDetailResponse from(Problem problem, List<TestCaseDTO> testCases) {
        return ProblemDetailResponse.builder()
                .id(problem.getId())
                .num(problem.getNum())
                .title(problem.getTitle())
                .description(problem.getDescription())
                .testCases(testCases)
                .build();
    }
}

