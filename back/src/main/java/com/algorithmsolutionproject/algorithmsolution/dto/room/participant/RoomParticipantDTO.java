package com.algorithmsolutionproject.algorithmsolution.dto.room.participant;

import com.algorithmsolutionproject.algorithmsolution.entity.User;

public record RoomParticipantDTO(
        Integer id,
        String username,
        String email
) {
    public static RoomParticipantDTO from(User user) {
        return new RoomParticipantDTO(user.getId(), user.getUserName(), user.getEmail());
    }
}
