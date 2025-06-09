package com.algorithmsolutionproject.algorithmsolution.controller.socket;

import com.algorithmsolutionproject.algorithmsolution.dto.socket.EnterRoomRequest;
import com.algorithmsolutionproject.algorithmsolution.service.room.RoomSocketService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
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
}
