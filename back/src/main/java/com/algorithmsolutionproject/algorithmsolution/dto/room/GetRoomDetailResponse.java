package com.algorithmsolutionproject.algorithmsolution.dto.room;

import com.algorithmsolutionproject.algorithmsolution.dto.problem.GetRoomDetailProblemDTO;
import com.algorithmsolutionproject.algorithmsolution.entity.Room;
import java.util.List;
import lombok.Builder;

@Builder
public record GetRoomDetailResponse(
        Integer id,
        String title,
        Integer duration,
        String language,
        List<GetRoomDetailProblemDTO> problems
) {
    public static GetRoomDetailResponse from(Room room, List<GetRoomDetailProblemDTO> problems) {
        return GetRoomDetailResponse.builder()
                .id(room.getId())
                .title(room.getTitle())
                .duration(room.getDuration())
                .language(room.getLanguage())
                .problems(problems)
                .build();
    }
}
