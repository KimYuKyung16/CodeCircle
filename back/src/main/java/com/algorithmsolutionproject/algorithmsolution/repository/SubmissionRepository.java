package com.algorithmsolutionproject.algorithmsolution.repository;

import com.algorithmsolutionproject.algorithmsolution.entity.Submission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    List<Submission> findByRoomIdAndUserIdAndProblemId(Integer userId, Integer roomId, Integer problemId);

    List<Submission> findByUserIdAndProblemId(Integer userId, Integer problemId);

    @Query(value = """
            SELECT s.*
            FROM submissions s
            JOIN (
                SELECT user_id, MAX(created_at) AS max_created_at
                FROM submissions
                WHERE room_id = :roomId
                GROUP BY user_id
            ) latest
            ON s.user_id = latest.user_id AND s.created_at = latest.max_created_at
            WHERE s.room_id = :roomId
            """, nativeQuery = true)
    List<Submission> findLatestSubmissionsPerUserInRoom(@Param("roomId") Integer roomId);
}
