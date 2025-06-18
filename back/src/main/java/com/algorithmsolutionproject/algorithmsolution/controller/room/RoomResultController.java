package com.algorithmsolutionproject.algorithmsolution.controller.room;

import com.algorithmsolutionproject.algorithmsolution.dto.common.ApiResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.GetSolvedProblemResultResponse;
import com.algorithmsolutionproject.algorithmsolution.service.room.RoomResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/rooms")
public class RoomResultController {
    private final RoomResultService roomResultService;

    // 문제 풀이 최종 결과
    @GetMapping("/{roomId}/result")
    public ResponseEntity<ApiResponse<GetSolvedProblemResultResponse>> getSolveProblemResult(
            @PathVariable("roomId") Integer roomId) {
        GetSolvedProblemResultResponse response = roomResultService.getSolveProblemResult(roomId);
        return ResponseEntity.ok(ApiResponse.success("결과를 성공적으로 조회했습니다.", response));
    }

}
