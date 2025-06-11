package com.algorithmsolutionproject.algorithmsolution.dto.room;

import com.algorithmsolutionproject.algorithmsolution.dto.problem.RunTestCaseDTO;
import java.util.List;

public record ExecuteCodeAndStoreResultResponse(
        List<RunTestCaseDTO> results
) {
    public static ExecuteCodeAndStoreResultResponse from(List<RunTestCaseDTO> results) {
        return new ExecuteCodeAndStoreResultResponse(results);
    }
}
