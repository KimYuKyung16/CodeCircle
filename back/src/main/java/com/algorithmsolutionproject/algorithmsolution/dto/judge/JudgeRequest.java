package com.algorithmsolutionproject.algorithmsolution.dto.judge;

import com.algorithmsolutionproject.algorithmsolution.entity.Execution;
import lombok.Builder;

@Builder
public record JudgeRequest(
        Integer executionId,
        String code,
        String language,
        Execution.Status status,
        Integer problemId
) {
}
