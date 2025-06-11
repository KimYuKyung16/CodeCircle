package com.algorithmsolutionproject.algorithmsolution.repository;

import com.algorithmsolutionproject.algorithmsolution.entity.Submission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    List<Submission> findByRoomIdAndUserIdAndProblemId(Integer userId, Integer roomId, Integer problemId);

    List<Submission> findByUserIdAndProblemId(Integer userId, Integer problemId);
}
