package com.algorithmsolutionproject.algorithmsolution.repository;

import com.algorithmsolutionproject.algorithmsolution.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query("""
                SELECT u FROM User u
                JOIN RoomParticipant rp ON u.id = rp.id.userId
                WHERE rp.id.roomId = :roomId AND rp.isLeaved = false
            """)
    List<User> findUsersByRoomId(@Param("roomId") int roomId);
}
