package com.algorithmsolutionproject.algorithmsolution.entity;

import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomProblemId implements Serializable {

    private int roomId;
    private int problemId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomProblemId that)) return false;
        return roomId == that.roomId && problemId == that.problemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, problemId);
    }
}
