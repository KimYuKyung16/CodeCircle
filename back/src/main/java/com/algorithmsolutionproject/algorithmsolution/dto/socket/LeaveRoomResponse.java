package com.algorithmsolutionproject.algorithmsolution.dto.socket;

import com.algorithmsolutionproject.algorithmsolution.entity.User;
import java.util.List;

public record LeaveRoomResponse(
        String message,
        List<User> participants
) {
}
