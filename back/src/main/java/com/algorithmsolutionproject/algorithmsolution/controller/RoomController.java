package com.algorithmsolutionproject.algorithmsolution.controller;

import com.algorithmsolutionproject.algorithmsolution.dto.common.ApiResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.GetAllRoomsResponse;
import com.algorithmsolutionproject.algorithmsolution.service.RoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/rooms")
public class RoomController {
    private final RoomService roomService;

    // 방 전체 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<GetAllRoomsResponse>>> getAllRooms() {
        List<GetAllRoomsResponse> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(ApiResponse.success("방을 성공적으로 조회했습니다", rooms));
    }
}
