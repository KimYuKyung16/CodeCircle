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
public class RoomUserId implements Serializable {
    private int roomId;
    private int userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomUserId that)) return false;
        return roomId == that.roomId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, userId);
    }
}
