package com.algorithmsolutionproject.algorithmsolution.service.room;

import com.algorithmsolutionproject.algorithmsolution.dto.room.CreateRoomRequest;
import com.algorithmsolutionproject.algorithmsolution.dto.room.CreateRoomResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.GetAllRoomsResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.GetRoomDetailProblemDTO;
import com.algorithmsolutionproject.algorithmsolution.dto.room.GetRoomDetailResponse;
import com.algorithmsolutionproject.algorithmsolution.entity.Room;
import com.algorithmsolutionproject.algorithmsolution.entity.RoomParticipant;
import com.algorithmsolutionproject.algorithmsolution.entity.RoomProblem;
import com.algorithmsolutionproject.algorithmsolution.entity.RoomUserId;
import com.algorithmsolutionproject.algorithmsolution.entity.User;
import com.algorithmsolutionproject.algorithmsolution.repository.RoomParticipantRepository;
import com.algorithmsolutionproject.algorithmsolution.repository.RoomProblemRepository;
import com.algorithmsolutionproject.algorithmsolution.repository.RoomRepository;
import com.algorithmsolutionproject.algorithmsolution.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomProblemRepository roomProblemRepository;
    private final RoomParticipantRepository roomParticipantRepository;
    private final UserRepository userRepository;

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
        User host = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));
        Room savedRoom = saveRoom(createRoomRequest, host);
        addSelectedProblems(savedRoom.getId(), createRoomRequest.problems());
        System.out.println("1");
        registerHost(savedRoom, host);
        System.out.println("2");
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

    // 방 저장
    private Room saveRoom(CreateRoomRequest request, User host) {
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
                .host(host)
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
    private void registerHost(Room room, User user) {
        RoomUserId id = new RoomUserId(room.getId(), user.getId());
        RoomParticipant host = RoomParticipant.builder()
                .id(id)
                .room(room)
                .user(user)
                .role(RoomParticipant.Role.HOST)
                .build();
        roomParticipantRepository.save(host);
    }
}
