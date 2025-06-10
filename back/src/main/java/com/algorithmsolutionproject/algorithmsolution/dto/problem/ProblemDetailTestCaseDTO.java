package com.algorithmsolutionproject.algorithmsolution.dto.problem;

import com.algorithmsolutionproject.algorithmsolution.entity.TestCase;
import lombok.Builder;

@Builder
public record ProblemDetailTestCaseDTO(
        Long id,
        String input,
        String expectedOutput,
        boolean isSample
) {
    public static ProblemDetailTestCaseDTO from(TestCase testCase) {
        return ProblemDetailTestCaseDTO.builder()
                .id(testCase.getId())
                .input(testCase.getInput())
                .expectedOutput(testCase.getExpectedOutput())
                .isSample(testCase.isSample())
                .build();
    }
}
