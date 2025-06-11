package com.algorithmsolutionproject.algorithmsolution.dto.room;

import com.algorithmsolutionproject.algorithmsolution.entity.Submission;
import java.util.List;

public record GetSolvedProblemResultResponse(
        List<SubmissionDTO> results
) {
    public static GetSolvedProblemResultResponse from(List<Submission> submissions) {
        return new GetSolvedProblemResultResponse(
                submissions.stream()
                        .map(SubmissionDTO::from)
                        .toList()
        );
    }
}
