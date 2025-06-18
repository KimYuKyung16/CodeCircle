package com.algorithmsolutionproject.algorithmsolution.dto.problem;

import com.algorithmsolutionproject.algorithmsolution.dto.room.submission.SubmissionDTO;
import com.algorithmsolutionproject.algorithmsolution.entity.Submission;
import java.util.List;

public record SubmissonsByProblemIdResponse(
        List<SubmissionDTO> submissions
) {
    public static SubmissonsByProblemIdResponse from(List<Submission> submissions) {
        return new SubmissonsByProblemIdResponse(
                submissions.stream()
                        .map(SubmissionDTO::from)
                        .toList()
        );
    }
}

