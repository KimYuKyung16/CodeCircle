package com.algorithmsolutionproject.algorithmsolution.dto.room;

import com.algorithmsolutionproject.algorithmsolution.entity.Execution;
import java.util.List;

public record GetExecutionListInRoomResonse(
        List<ExecutionDTO> executions
) {
    public static GetExecutionListInRoomResonse from(List<Execution> executions) {
        return new GetExecutionListInRoomResonse(
                executions.stream()
                        .map(ExecutionDTO::from)
                        .toList()
        );
    }
}
