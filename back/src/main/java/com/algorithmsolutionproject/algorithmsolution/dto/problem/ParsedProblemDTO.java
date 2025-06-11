package com.algorithmsolutionproject.algorithmsolution.dto.problem;

import java.util.List;
import lombok.Builder;

@Builder
public record ParsedProblemDTO(
        int num,
        String title,
        String description,
        List<String> sampleInputs,
        List<String> sampleOutputs
) {
}
