package com.algorithmsolutionproject.algorithmsolution.controller.socket;

import com.algorithmsolutionproject.algorithmsolution.dto.socket.EnterRoomRequest;
import com.algorithmsolutionproject.algorithmsolution.security.AuthenticatedUser;
import com.algorithmsolutionproject.algorithmsolution.service.room.RoomSocketService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RoomSocketController {
    private final RoomSocketService roomSocketService;

    // 방 접속
    @MessageMapping("/room/enter")
    public void enterRoom(EnterRoomRequest request, Principal principal) {
        Integer roomId = request.roomId();
        String userEmail = principal.getName();
        roomSocketService.enterRoom(roomId, userEmail);
    }

    // 방 나가기
    @MessageMapping("/room/leave")
    public void leaveRoom(EnterRoomRequest request, Principal principal) {
        AuthenticatedUser user = (AuthenticatedUser) ((Authentication) principal).getPrincipal();
        Integer roomId = request.roomId();
        Integer userId = user.userId();
        String userEmail = user.email();
        roomSocketService.leaveRoom(roomId, userId, userEmail);
    }
}
