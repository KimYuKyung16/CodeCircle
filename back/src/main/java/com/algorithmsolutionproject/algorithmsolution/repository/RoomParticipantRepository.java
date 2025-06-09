package com.algorithmsolutionproject.algorithmsolution.repository;

import com.algorithmsolutionproject.algorithmsolution.entity.RoomParticipant;
import com.algorithmsolutionproject.algorithmsolution.entity.RoomUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomParticipantRepository extends JpaRepository<RoomParticipant, RoomUserId> {
}
