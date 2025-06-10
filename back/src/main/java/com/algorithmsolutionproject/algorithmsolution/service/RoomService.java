package com.algorithmsolutionproject.algorithmsolution.service;

import com.algorithmsolutionproject.algorithmsolution.dto.room.CreateRoomRequest;
import com.algorithmsolutionproject.algorithmsolution.dto.room.CreateRoomResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.GetAllRoomsResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.GetRoomDetailProblemDTO;
import com.algorithmsolutionproject.algorithmsolution.dto.room.GetRoomDetailResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.StartSolveProblemResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.TimerEndResponse;
import com.algorithmsolutionproject.algorithmsolution.entity.Room;
import com.algorithmsolutionproject.algorithmsolution.entity.RoomParticipant;
import com.algorithmsolutionproject.algorithmsolution.entity.RoomProblem;
import com.algorithmsolutionproject.algorithmsolution.entity.RoomUserId;
import com.algorithmsolutionproject.algorithmsolution.repository.RoomParticipantRepository;
import com.algorithmsolutionproject.algorithmsolution.repository.RoomProblemRepository;
import com.algorithmsolutionproject.algorithmsolution.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomProblemRepository roomProblemRepository;
    private final RoomParticipantRepository roomParticipantRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // 방 전체 조회
    @Transactional
    public List<GetAllRoomsResponse> getAllRooms() {
        List<Room> activeRooms = roomRepository.findByStatusNot(Room.RoomStatus.FINISHED);
        return GetAllRoomsResponse.fromList(activeRooms);
    }

    // 방 생성
    @Transactional
    public CreateRoomResponse createRoom(Integer userId, CreateRoomRequest createRoomRequest) {
        if (userId == null) {
            throw new IllegalArgumentException("userId는 필수입니다.");
        }
        Room savedRoom = saveRoom(createRoomRequest);
        addSelectedProblems(savedRoom.getId(), createRoomRequest.problems());
        registerHost(savedRoom.getId(), userId);
        return new CreateRoomResponse(savedRoom.getId());
    }

    // 방 상세 조회
    @Transactional
    public GetRoomDetailResponse getRoomDetail(int roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("해당 방이 없습니다."));
        List<GetRoomDetailProblemDTO> problems = roomProblemRepository.findProblemsByRoomId(roomId);
        return GetRoomDetailResponse.from(room, problems);
    }

    // 문제풀이 시작
    @Transactional
    public void startSolveProblem(Integer roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방입니다."));
//        if (!room.isHost(userId)) {
//            throw new AccessDeniedException("방장만 게임을 시작할 수 있습니다.");
//        }
        if (room.getStatus() != Room.RoomStatus.WAITING) {
            throw new IllegalStateException("이미 시작된 게임입니다.");
        }
        room.setStatus(Room.RoomStatus.PLAYING);
        roomRepository.save(room);

        long now = System.currentTimeMillis();
        int duration = room.getDuration();

        StartSolveProblemResponse response = new StartSolveProblemResponse("게임이 시작되었습니다", now, duration);
        messagingTemplate.convertAndSend("/topic/room/" + roomId + "/start", response);

        scheduler.schedule(() -> {
            messagingTemplate.convertAndSend("/topic/room/" + roomId + "/end", new TimerEndResponse("타이머 종료"));
        }, duration, TimeUnit.SECONDS);
    }

    // 방 저장
    private Room saveRoom(CreateRoomRequest request) {
        if (request.title() == null || request.title().isBlank()) {
            throw new IllegalArgumentException("방 제목은 필수입니다.");
        }
        if (request.duration() == null || request.duration() <= 0) {
            throw new IllegalArgumentException("방 지속 시간은 0보다 커야 합니다.");
        }
        if (request.language() == null || request.language().isBlank()) {
            throw new IllegalArgumentException("사용 언어는 필수입니다.");
        }

        Room room = Room.builder()
                .title(request.title())
                .duration(request.duration())
                .language(request.language())
                .password(request.password())
                .build();

        return roomRepository.save(room);
    }

    // 선택한 문제 추가
    private void addSelectedProblems(Integer roomId, List<Integer> problems) {
        for (Integer problemId : problems) {
            roomProblemRepository.save(RoomProblem.builder()
                    .roomId(roomId)
                    .problemId(problemId)
                    .build());
        }
    }

    // 방장으로 등록
    private void registerHost(Integer roomId, Integer userId) {
        RoomUserId id = new RoomUserId(roomId, userId);
        RoomParticipant host = RoomParticipant.builder()
                .id(id)
                .role(RoomParticipant.Role.HOST)
                .build();
        roomParticipantRepository.save(host);
    }
}
