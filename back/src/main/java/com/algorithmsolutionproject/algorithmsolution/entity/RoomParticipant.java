package com.algorithmsolutionproject.algorithmsolution.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RoomParticipant {
    @EmbeddedId
    private RoomUserId id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roomId") // ✅ EmbeddedId 안의 필드명과 맞춰야 함
    @JoinColumn(name = "room_id")
    private Room room;

    public void leave() {
        this.isLeaved = true;
        this.leavedAt = LocalDateTime.now();
    }

    public enum Role {
        HOST,
        PARTICIPANT
    }
}
