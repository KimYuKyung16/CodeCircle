package com.algorithmsolutionproject.algorithmsolution.dto.room.detail;

import com.algorithmsolutionproject.algorithmsolution.entity.Room;
import java.util.List;
import lombok.Builder;

@Builder
public record GetRoomDetailResponse(
        Integer id,
        String title,
        Integer duration,
        String language,
        String host,
        List<GetRoomDetailProblemDTO> problems
) {
    public static GetRoomDetailResponse from(Room room, List<GetRoomDetailProblemDTO> problems) {
        return GetRoomDetailResponse.builder()
                .id(room.getId())
                .title(room.getTitle())
                .duration(room.getDuration())
                .language(room.getLanguage())
                .host(room.getHost().getUserName())
                .problems(problems)
                .build();
    }
}
