package com.algorithmsolutionproject.algorithmsolution.controller.room;

import com.algorithmsolutionproject.algorithmsolution.dto.common.ApiResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.EnterRoomRequest;
import com.algorithmsolutionproject.algorithmsolution.dto.room.GetRoomParticipantsResponse;
import com.algorithmsolutionproject.algorithmsolution.security.CustomUserPrincipal;
import com.algorithmsolutionproject.algorithmsolution.service.room.RoomParticipationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/rooms")
public class RoomParticipationController {
    private final RoomParticipationService roomParticipationService;

    // 방 접속
    @PostMapping("/{roomId}/enter")
    public ResponseEntity<ApiResponse<Void>> enterRoom(Authentication authentication,
                                                       @PathVariable("roomId") Integer roomId,
                                                       @Valid @RequestBody EnterRoomRequest request) {
        CustomUserPrincipal principal = (CustomUserPrincipal) authentication.getPrincipal();
        int userId = principal.userId();
        roomParticipationService.enterRoom(userId, roomId, request.password());
        return ResponseEntity.ok(ApiResponse.success("방에 성공적으로 접속했습니다.", null));
    }

    // 방 참여자 조회
    @GetMapping("/{roomId}/participants")
    public ResponseEntity<ApiResponse<GetRoomParticipantsResponse>> getRoomParticipants(@PathVariable("roomId") Integer roomId) {
        if (roomId == null) {
            throw new IllegalArgumentException("roomId는 필수입니다.");
        }
        GetRoomParticipantsResponse response = roomParticipationService.getRoomParticipants(roomId);
        return ResponseEntity.ok(ApiResponse.success("방 참여자들을 성공적으로 조회했습니다.", response));
    }

}
