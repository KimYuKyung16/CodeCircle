package com.algorithmsolutionproject.algorithmsolution.repository;

import com.algorithmsolutionproject.algorithmsolution.dto.room.detail.GetRoomDetailProblemDTO;
import com.algorithmsolutionproject.algorithmsolution.entity.RoomProblem;
import com.algorithmsolutionproject.algorithmsolution.entity.RoomProblemId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomProblemRepository extends JpaRepository<RoomProblem, RoomProblemId> {
    @Query("""
                SELECT new com.algorithmsolutionproject.algorithmsolution.dto.room.GetRoomDetailProblemDTO(
                    p.id, p.num, p.title
                )
                FROM RoomProblem rp
                JOIN rp.problem p
                WHERE rp.room.id = :roomId
            """)
    List<GetRoomDetailProblemDTO> findProblemsByRoomId(@Param("roomId") int roomId);
}
