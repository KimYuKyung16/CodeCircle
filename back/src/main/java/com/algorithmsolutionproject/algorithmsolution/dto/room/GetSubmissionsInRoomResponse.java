package com.algorithmsolutionproject.algorithmsolution.dto.room;

import com.algorithmsolutionproject.algorithmsolution.entity.Submission;
import java.util.List;

public record GetSubmissionsInRoomResponse(
        List<SubmissionDTO> submissions
) {
    public static GetSubmissionsInRoomResponse from(List<Submission> submissions) {
        return new GetSubmissionsInRoomResponse(
                submissions.stream()
                        .map(SubmissionDTO::from)
                        .toList()
        );
    }
}
