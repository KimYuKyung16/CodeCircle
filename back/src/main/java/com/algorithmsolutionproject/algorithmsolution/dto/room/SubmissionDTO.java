package com.algorithmsolutionproject.algorithmsolution.dto.room;

import com.algorithmsolutionproject.algorithmsolution.entity.Submission;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record SubmissionDTO(
        Integer id,
        String language,
        String code,
        String result,
        String errorMessage,
        Integer executionTime,
        Integer memory,
        LocalDateTime createdAt
) {
    public static SubmissionDTO from(Submission submission) {
        return SubmissionDTO.builder()
                .id(submission.getId())
                .language(submission.getLanguage())
                .code(submission.getCode())
                .result(submission.getResult())
                .errorMessage(submission.getErrorMessage())
                .executionTime(submission.getExecutionTime())
                .memory(submission.getMemory())
                .createdAt(submission.getCreatedAt())
                .build();
    }
}
