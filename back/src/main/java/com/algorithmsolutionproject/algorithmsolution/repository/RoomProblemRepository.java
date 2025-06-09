package com.algorithmsolutionproject.algorithmsolution.repository;

import com.algorithmsolutionproject.algorithmsolution.entity.RoomProblem;
import com.algorithmsolutionproject.algorithmsolution.entity.RoomProblemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomProblemRepository extends JpaRepository<RoomProblem, RoomProblemId> {
}
