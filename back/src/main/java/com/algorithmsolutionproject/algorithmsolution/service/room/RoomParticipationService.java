package com.algorithmsolutionproject.algorithmsolution.service.room;

import com.algorithmsolutionproject.algorithmsolution.dto.room.GetRoomParticipantsResponse;
import com.algorithmsolutionproject.algorithmsolution.entity.Room;
import com.algorithmsolutionproject.algorithmsolution.entity.RoomParticipant;
import com.algorithmsolutionproject.algorithmsolution.entity.RoomUserId;
import com.algorithmsolutionproject.algorithmsolution.entity.User;
import com.algorithmsolutionproject.algorithmsolution.repository.RoomParticipantRepository;
import com.algorithmsolutionproject.algorithmsolution.repository.RoomRepository;
import com.algorithmsolutionproject.algorithmsolution.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomParticipationService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final RoomParticipantRepository roomParticipantRepository;
    private final SimpMessagingTemplate messagingTemplate;

    // 방 접속
    @Transactional
    public void enterRoom(Integer userId, Integer roomId, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 방입니다."));

        if (room.getParticipants().size() >= 8) {
            throw new IllegalArgumentException("수용 인원을 초과했습니다.");
        }

        // 패스워드가 있는 방일 경우
        if (room.getPassword() != null && !room.getPassword().isBlank()) {
            if (!room.getPassword().equals(password)) {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        }

        RoomUserId id = new RoomUserId(roomId, userId);
        boolean alreadyUserExists = roomParticipantRepository.existsById(id);
        if (!alreadyUserExists) {
            RoomParticipant participant = RoomParticipant.builder()
                    .id(id)
                    .role(RoomParticipant.Role.PARTICIPANT)
                    .room(room)
                    .user(user)
                    .build();
            roomParticipantRepository.save(participant);
        }

        messagingTemplate.convertAndSend("/topic/room/" + roomId + "/participants", "update");
    }

    // 방 참여자 조회
    @Transactional
    public GetRoomParticipantsResponse getRoomParticipants(int roomId) {
        List<RoomParticipant> participants = roomParticipantRepository.findWithParticipantUserByRoomId(roomId);

        List<User> users = participants.stream()
                .map(RoomParticipant::getUser)
                .collect(Collectors.toList());

        return GetRoomParticipantsResponse.from(users);
    }

}
