package com.algorithmsolutionproject.algorithmsolution.repository;

import com.algorithmsolutionproject.algorithmsolution.entity.RoomParticipant;
import com.algorithmsolutionproject.algorithmsolution.entity.RoomUserId;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import java.util.List;

public interface RoomParticipantRepository extends JpaRepository<RoomParticipant, RoomUserId> {
    boolean existsById(@NonNull RoomUserId id);

    @Query("""
    SELECT rp FROM RoomParticipant rp
    JOIN FETCH rp.user u
    WHERE rp.room.id = :roomId AND rp.role <> 'HOST'
    """)
    List<RoomParticipant> findWithParticipantUserByRoomId(@Param("roomId") int roomId);
}
