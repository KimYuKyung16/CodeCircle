package com.algorithmsolutionproject.algorithmsolution.controller;

import com.algorithmsolutionproject.algorithmsolution.dto.common.ApiResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.ExecuteCodeAndStoreResultRequest;
import com.algorithmsolutionproject.algorithmsolution.dto.room.GetExecutionListInRoomResonse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.GetSubmissionsInRoomResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.SubmitCodeRequest;
import com.algorithmsolutionproject.algorithmsolution.dto.room.SubmitCodeResponse;
import com.algorithmsolutionproject.algorithmsolution.security.CustomUserPrincipal;
import com.algorithmsolutionproject.algorithmsolution.service.CodeService;
import com.algorithmsolutionproject.algorithmsolution.service.RoomService;
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
public class RoomCodeController {
    private final RoomService roomService;
    private final CodeService codeService;

    // 특정 문제에 대한 내 코드 실행 내역 조회
    @GetMapping("/{roomId}/problems/{problemId}/executions")
    public ResponseEntity<ApiResponse<GetExecutionListInRoomResonse>> getExecutionListInRoom(Authentication authentication,
                                                                                             @PathVariable("roomId") Integer roomId,
                                                                                             @PathVariable("problemId") Integer problemId) {
        CustomUserPrincipal principal = (CustomUserPrincipal) authentication.getPrincipal();
        int userId = principal.userId();
        GetExecutionListInRoomResonse response = roomService.getExecutionListInRoom(userId, roomId, problemId);
        return ResponseEntity.ok(ApiResponse.success("실행 내역을 성공적으로 조회했습니다.", response));
    }

    // 특정 문제에 대한 내 제출 내역 조회
    @GetMapping("/{roomId}/problems/{problemId}/submissions")
    public ResponseEntity<ApiResponse<GetSubmissionsInRoomResponse>> getSubmttionsInRoom(Authentication authentication,
                                                                                         @PathVariable("roomId") Integer roomId,
                                                                                         @PathVariable("problemId") Integer problemId) {
        CustomUserPrincipal principal = (CustomUserPrincipal) authentication.getPrincipal();
        int userId = principal.userId();
        GetSubmissionsInRoomResponse response = roomService.getSubmissionsInRoom(userId, roomId, problemId);
        return ResponseEntity.ok(ApiResponse.success("제출 내역을 성공적으로 조회했습니다.", response));
    }

    // 코드 실행
    @PostMapping("/{roomId}/problems/{problemId}/execution")
    public ResponseEntity<ApiResponse<Void>> executeCodeAndStoreResult(
            Authentication authentication,
            @PathVariable("roomId") Integer roomId,
            @PathVariable("problemId") Integer problemId,
            @RequestBody ExecuteCodeAndStoreResultRequest request
    ) {
        CustomUserPrincipal principal = (CustomUserPrincipal) authentication.getPrincipal();
        int userId = principal.userId();
        codeService.executeCode(userId, roomId, problemId, request.code());
        return ResponseEntity.ok(ApiResponse.success("코드를 실행하고 있습니다.", null));
    }

    // 코드 제출
    @PostMapping("/{roomId}/problems/{problemId}/submit")
    public ResponseEntity<ApiResponse<SubmitCodeResponse>> submitCode(Authentication authentication,
                                                                      @PathVariable("roomId") Integer roomId,
                                                                      @PathVariable("problemId") Integer problemId,
                                                                      @RequestBody SubmitCodeRequest request) {
        CustomUserPrincipal principal = (CustomUserPrincipal) authentication.getPrincipal();
        int userId = principal.userId();
        SubmitCodeResponse response = codeService.submitCode(userId, roomId, problemId, request.code());
        return ResponseEntity.ok(ApiResponse.success("코드를 성공적으로 제출했습니다.", response));
    }

}
