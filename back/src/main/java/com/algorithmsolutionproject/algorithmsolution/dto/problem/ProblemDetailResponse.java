package com.algorithmsolutionproject.algorithmsolution.dto.problem;

import com.algorithmsolutionproject.algorithmsolution.entity.Problem;
import com.algorithmsolutionproject.algorithmsolution.entity.TestCase;
import java.util.List;
import lombok.Builder;

@Builder
public record ProblemDetailResponse(
        int id,
        int num,
        String title,
        String description,
        List<ProblemDetailTestCaseDTO> testCases
) {
    public static ProblemDetailResponse from(Problem problem, List<TestCase> testCases) {
        List<ProblemDetailTestCaseDTO> testCaseDtoList = testCases.stream()
                .map(ProblemDetailTestCaseDTO::from)
                .toList();

        return ProblemDetailResponse.builder()
                .id(problem.getId())
                .num(problem.getNum())
                .title(problem.getTitle())
                .description(problem.getDescription())
                .testCases(testCaseDtoList)
                .build();
    }
}

