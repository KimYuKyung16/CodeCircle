package com.algorithmsolutionproject.algorithmsolution.repository;

import com.algorithmsolutionproject.algorithmsolution.entity.Execution;
import com.algorithmsolutionproject.algorithmsolution.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExecutionRepository extends JpaRepository<Execution, Integer> {
    List<Execution> findByRoomIdAndUserIdAndProblemId(Integer userId, Integer roomId, Integer problemId);
}
