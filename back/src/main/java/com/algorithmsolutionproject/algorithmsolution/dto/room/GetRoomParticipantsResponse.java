package com.algorithmsolutionproject.algorithmsolution.dto.room;

import com.algorithmsolutionproject.algorithmsolution.entity.User;
import lombok.Builder;
import java.util.List;

@Builder
public record GetRoomParticipantsResponse(List<RoomParticipantDTO> participants) {
    public static GetRoomParticipantsResponse from(List<User> users) {
        List<RoomParticipantDTO> dtos = users.stream()
                .map(RoomParticipantDTO::from)
                .toList();
        return new GetRoomParticipantsResponse(dtos);
    }
}
