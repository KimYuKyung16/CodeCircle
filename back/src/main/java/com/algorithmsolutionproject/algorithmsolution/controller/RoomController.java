package com.algorithmsolutionproject.algorithmsolution.controller;

import com.algorithmsolutionproject.algorithmsolution.dto.common.ApiResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.CreateRoomRequest;
import com.algorithmsolutionproject.algorithmsolution.dto.room.CreateRoomResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.ExecuteCodeAndStoreResultRequest;
import com.algorithmsolutionproject.algorithmsolution.dto.room.ExecuteCodeAndStoreResultResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.GetAllRoomsResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.GetRoomDetailResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.GetSolvedProblemResultResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.GetSubmissionsInRoomResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.SubmitCodeRequest;
import com.algorithmsolutionproject.algorithmsolution.dto.room.SubmitCodeResponse;
import com.algorithmsolutionproject.algorithmsolution.security.CustomUserPrincipal;
import com.algorithmsolutionproject.algorithmsolution.service.CodeService;
import com.algorithmsolutionproject.algorithmsolution.service.RoomService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/rooms")
public class RoomController {
    private final RoomService roomService;
    private final CodeService codeService;

    // 방 전체 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<GetAllRoomsResponse>>> getAllRooms() {
        List<GetAllRoomsResponse> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(ApiResponse.success("방을 성공적으로 조회했습니다.", rooms));
    }

    // 방 생성
    @PostMapping
    public ResponseEntity<ApiResponse<CreateRoomResponse>> createRoom(Authentication authentication,
                                                                      @Valid @RequestBody CreateRoomRequest request) {
        CustomUserPrincipal principal = (CustomUserPrincipal) authentication.getPrincipal();
        int userId = principal.userId();
        CreateRoomResponse response = roomService.createRoom(userId, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("방이 성공적으로 생성되었습니다.", response));
    }

    // 방 상세 조회
    @GetMapping("/{roomId}")
    public ResponseEntity<ApiResponse<GetRoomDetailResponse>> getRoomDetail(@PathVariable("roomId") Integer roomId) {
        if (roomId == null) {
            throw new IllegalArgumentException("roomId는 필수입니다.");
        }

        GetRoomDetailResponse response = roomService.getRoomDetail(roomId);
        return ResponseEntity.ok(ApiResponse.success("방을 성공적으로 조회했습니다.", response));
    }

    // 문제풀이 시작
    @PatchMapping("/{roomId}/start")
    public ResponseEntity<ApiResponse<Void>> startSolveProblem(@PathVariable("roomId") Integer roomId) {
        roomService.startSolveProblem(roomId);
        return ResponseEntity.ok(ApiResponse.success("문제 풀이가 시작되었습니다.", null));
    }

    // 문제풀이 종료
    @PatchMapping("/{roomId}/end")
    public ResponseEntity<ApiResponse<Void>> endSolveProblem(Authentication authentication,
                                                             @PathVariable("roomId") Integer roomId) {
        CustomUserPrincipal principal = (CustomUserPrincipal) authentication.getPrincipal();
        int userId = principal.userId();
        roomService.endSolveProblem(roomId, userId);
        return ResponseEntity.ok(ApiResponse.success("문제 풀이가 종료되었습니다.", null));
    }

    // 코드 실행 결과 조회
    @PostMapping("/{roomId}/problems/{problemId}/execution")
    public ResponseEntity<ApiResponse<ExecuteCodeAndStoreResultResponse>> executeCodeAndStoreResult(
            @PathVariable("roomId") Integer roomId,
            @PathVariable("problemId") Integer problemId,
            @RequestBody ExecuteCodeAndStoreResultRequest request
    ) {
        ExecuteCodeAndStoreResultResponse response = codeService.executeCode(roomId, problemId, request.code());
        return ResponseEntity.ok(ApiResponse.success("코드 실행 결과입니다.", response));
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

    // 문제 풀이 최종 결과
    @GetMapping("/{roomId}/result")
    public ResponseEntity<ApiResponse<GetSolvedProblemResultResponse>> getSolveProblemResult(
            @PathVariable("roomId") Integer roomId) {
        GetSolvedProblemResultResponse response = roomService.getSolveProblemResult(roomId);
        return ResponseEntity.ok(ApiResponse.success("결과를 성공적으로 조회했습니다.", response));
    }
}
