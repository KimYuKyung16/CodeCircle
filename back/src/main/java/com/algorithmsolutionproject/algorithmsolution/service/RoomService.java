package com.algorithmsolutionproject.algorithmsolution.service;

import com.algorithmsolutionproject.algorithmsolution.dto.room.GetAllRoomsResponse;
import com.algorithmsolutionproject.algorithmsolution.entity.Room;
import com.algorithmsolutionproject.algorithmsolution.repository.RoomRepository;
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

    // 방 전체 조회
    @Transactional
    public List<GetAllRoomsResponse> getAllRooms() {
        List<Room> activeRooms = roomRepository.findByStatusNot(Room.RoomStatus.FINISHED);
        return GetAllRoomsResponse.fromList(activeRooms);
    }
}
