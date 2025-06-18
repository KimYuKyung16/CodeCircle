package com.algorithmsolutionproject.algorithmsolution.dto.room.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record CreateRoomRequest(
        @NotBlank(message = "방 제목은 필수입니다.")
        String title,

        @NotNull(message = "방 지속 시간은 필수입니다.")
        Integer duration,

        @NotBlank(message = "사용 언어는 필수입니다.")
        String language,

        String password, // 선택 값이므로 유효성 검사 안 붙임

        @NotEmpty(message = "문제 리스트는 최소 1개 이상이어야 합니다.")
        List<Integer> problems
) {
}
