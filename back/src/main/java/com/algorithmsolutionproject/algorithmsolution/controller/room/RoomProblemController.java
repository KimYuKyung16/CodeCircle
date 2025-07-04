package com.algorithmsolutionproject.algorithmsolution.controller.room;

import com.algorithmsolutionproject.algorithmsolution.dto.common.ApiResponse;
import com.algorithmsolutionproject.algorithmsolution.security.CustomUserPrincipal;
import com.algorithmsolutionproject.algorithmsolution.service.room.RoomProblemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/rooms")
public class RoomProblemController {
    private final RoomProblemService roomProblemService;

    // 문제풀이 시작
    @PatchMapping("/{roomId}/start")
    public ResponseEntity<ApiResponse<Void>> startSolveProblem(@PathVariable("roomId") Integer roomId) {
        roomProblemService.startSolveProblem(roomId);
        return ResponseEntity.ok(ApiResponse.success("문제 풀이가 시작되었습니다.", null));
    }

    // 문제풀이 종료
    @PatchMapping("/{roomId}/end")
    public ResponseEntity<ApiResponse<Void>> endSolveProblem(Authentication authentication,
                                                             @PathVariable("roomId") Integer roomId) {
        CustomUserPrincipal principal = (CustomUserPrincipal) authentication.getPrincipal();
        int userId = principal.userId();
        roomProblemService.endSolveProblem(roomId, userId);
        return ResponseEntity.ok(ApiResponse.success("문제 풀이가 종료되었습니다.", null));
    }
}
