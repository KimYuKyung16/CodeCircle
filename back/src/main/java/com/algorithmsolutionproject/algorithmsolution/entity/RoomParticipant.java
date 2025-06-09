package com.algorithmsolutionproject.algorithmsolution.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "room_participants")
@IdClass(RoomUserId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RoomParticipant {
    @Id
    @Column(name = "room_id")
    private int roomId;

    @Id
    @Column(name = "user_id")
    private int userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "joined_at", nullable = false, updatable = false)
    private LocalDateTime joinedAt;

    @Column(name = "leaved_at", nullable = true)
    private LocalDateTime leavedAt;

    @Builder.Default
    @Column(name = "is_leaved", nullable = false)
    private boolean isLeaved = false;

    @Builder.Default
    @Column(name = "is_solved", nullable = false)
    private boolean isSolved = false;

    @PrePersist
    public void prePersist() {
        this.joinedAt = LocalDateTime.now();
    }

    public enum Role {
        HOST,
        PARTICIPANT
    }
}
