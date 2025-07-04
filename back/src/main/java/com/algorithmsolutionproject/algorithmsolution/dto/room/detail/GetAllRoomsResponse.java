package com.algorithmsolutionproject.algorithmsolution.dto.room.detail;

import com.algorithmsolutionproject.algorithmsolution.entity.Room;
import java.util.List;
import lombok.Builder;

@Builder
public record GetAllRoomsResponse(
        Integer id,
        String title,
        String language,
        String status,
        boolean isPrivate,
        String host,
        Integer participantCount
) {
    public static GetAllRoomsResponse from(Room room) {
        return GetAllRoomsResponse.builder()
                .id(room.getId())
                .title(room.getTitle())
                .language(room.getLanguage())
                .status(room.getStatus().name())
                .isPrivate(room.getPassword() != null && !room.getPassword().isBlank())
                .host(room.getHost() != null ? room.getHost().getUserName() : null)
                .participantCount(
                        room.getParticipants() != null ? room.getParticipants().size() : 0
                )
                .build();
    }

    public static List<GetAllRoomsResponse> fromList(List<Room> rooms) {
        return rooms.stream().map(GetAllRoomsResponse::from).toList();
    }
}
