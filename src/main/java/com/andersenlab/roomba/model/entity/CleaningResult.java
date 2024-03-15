package com.andersenlab.roomba.model.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false)
    private int coordinateX;

    @Column(nullable = false)
    private int coordinateY;

    @Column(nullable = false)
    private int patches;

    public CleaningResult(int coordinateX, int coordinateY, int patches) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.patches = patches;
    }
}
