package com.algorithmsolutionproject.algorithmsolution.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "room_problems")
@IdClass(RoomProblemId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomProblem {
    @Id
    @Column(name = "room_id")
    private int roomId;

    @Id
    @Column(name = "problem_id")
    private int problemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    @JsonBackReference
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", insertable = false, updatable = false)
    private Problem problem;

    @Builder
    public RoomProblem(int roomId, int problemId) {
        this.roomId = roomId;
        this.problemId = problemId;
    }
}
