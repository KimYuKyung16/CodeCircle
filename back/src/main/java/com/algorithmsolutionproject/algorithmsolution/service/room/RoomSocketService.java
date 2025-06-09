package com.algorithmsolutionproject.algorithmsolution.service.room;

import com.algorithmsolutionproject.algorithmsolution.dto.socket.EnterRoomResponse;
import com.algorithmsolutionproject.algorithmsolution.entity.RoomParticipant;
import com.algorithmsolutionproject.algorithmsolution.entity.RoomUserId;
import com.algorithmsolutionproject.algorithmsolution.entity.User;
import com.algorithmsolutionproject.algorithmsolution.repository.RoomParticipantRepository;
import com.algorithmsolutionproject.algorithmsolution.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomSocketService {
    private final UserRepository userRepository;
    private final RoomParticipantRepository roomParticipantRepository;
    private final SimpMessagingTemplate messagingTemplate;

    // 방 접속
    @Transactional
    public void enterRoom(Integer roomId, String userEmail) {
        addParticipant(roomId, userEmail);
        EnterRoomResponse response = getRoomParticipants(roomId);
        messagingTemplate.convertAndSend("/topic/room/" + roomId + "/participants", response);
    }

    // 참여자 추가
    private void addParticipant(Integer roomId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        RoomUserId id = new RoomUserId(roomId, user.getId());
        boolean alreadyUserExists = roomParticipantRepository.existsById(id);
        if (!alreadyUserExists) {
            RoomParticipant participant = RoomParticipant.builder()
                    .id(id)
                    .role(RoomParticipant.Role.PARTICIPANT)
                    .build();
            roomParticipantRepository.save(participant);
        }
    }

    // 방 사용자 조회
    private EnterRoomResponse getRoomParticipants(Integer roomId) {
        List<User> participants = userRepository.findUsersByRoomId(roomId);
        return new EnterRoomResponse("새로운 사용자가 들어왔습니다.", participants);

    }
}
