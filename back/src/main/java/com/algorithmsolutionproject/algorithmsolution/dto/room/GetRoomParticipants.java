package com.algorithmsolutionproject.algorithmsolution.dto.room;

import com.algorithmsolutionproject.algorithmsolution.entity.User;
import lombok.Builder;
import java.util.List;

@Builder
public record GetRoomParticipants(
        String userName,
        String email
) {
    public static GetRoomParticipants from(User participant) {
        return GetRoomParticipants.builder()
                .userName(participant.getUserName())
                .email(participant.getEmail())
                .build();
    }

    public static List<GetRoomParticipants> fromList(List<User> participants) {
        return participants.stream()
                .map(GetRoomParticipants::from)
                .toList();
    }
}
