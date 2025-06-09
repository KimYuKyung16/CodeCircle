package com.algorithmsolutionproject.algorithmsolution.repository;

import com.algorithmsolutionproject.algorithmsolution.entity.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByStatusNot(Room.RoomStatus status);
}
