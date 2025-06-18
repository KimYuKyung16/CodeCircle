package com.algorithmsolutionproject.algorithmsolution.dto.room.execution;

import com.algorithmsolutionproject.algorithmsolution.dto.problem.RunTestCaseDTO;
import com.algorithmsolutionproject.algorithmsolution.entity.Execution;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record ExecutionDTO(
        Integer id,
        String language,
        String code,
        Execution.Status status,
        List<RunTestCaseDTO> outputs,
        String result,
        LocalDateTime createdAt
) {
    public static ExecutionDTO from(Execution execution) {
        return ExecutionDTO.builder()
                .id(execution.getId())
                .language(execution.getLanguage())
                .code(execution.getCode())
                .status(execution.getStatus())
                .outputs(execution.getOutput())
                .result(execution.getResult())
                .createdAt(execution.getCreatedAt())
                .build();
    }
}
