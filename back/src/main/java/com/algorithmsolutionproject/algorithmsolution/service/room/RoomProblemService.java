package com.algorithmsolutionproject.algorithmsolution.service.room;

import com.algorithmsolutionproject.algorithmsolution.dto.room.EndSolveProblemResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.StartSolveProblemResponse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.TimerEndResponse;
import com.algorithmsolutionproject.algorithmsolution.entity.Room;
import com.algorithmsolutionproject.algorithmsolution.entity.RoomParticipant;
import com.algorithmsolutionproject.algorithmsolution.entity.RoomUserId;
import com.algorithmsolutionproject.algorithmsolution.repository.RoomParticipantRepository;
import com.algorithmsolutionproject.algorithmsolution.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomProblemService {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final RoomRepository roomRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final RoomParticipantRepository roomParticipantRepository;

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

    // 문제풀이 종료
    @Transactional
    public void endSolveProblem(Integer roomId, Integer userId) {
        RoomUserId id = new RoomUserId(roomId, userId);
        RoomParticipant participant = roomParticipantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 참여자를 찾을 수 없습니다."));

        participant.setSolved(true);
        roomParticipantRepository.save(participant);

        EndSolveProblemResponse response = new EndSolveProblemResponse("풀이를 완료한 사람이 있습니다.");
        messagingTemplate.convertAndSend("/topic/room/" + roomId + "/result", response);
    }

}
