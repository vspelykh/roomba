package com.andersenlab.roomba.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "cleaning_result")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CleaningResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "incremental_integer_generator")
    private int id;

    @Column(name = "coordinate_x", nullable = false)
    private int coordinateX;

    @Column(name = "coordinate_y", nullable = false)
    private int coordinateY;

    @Column(nullable = false)
    private int patches;

    public CleaningResult(int coordinateX, int coordinateY, int patches) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.patches = patches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleaningResult that)) return false;
        return id == that.id && coordinateX == that.coordinateX && coordinateY == that.coordinateY && patches == that.patches;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coordinateX, coordinateY, patches);
    }
}
