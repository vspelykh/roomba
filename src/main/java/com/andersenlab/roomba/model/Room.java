package com.andersenlab.roomba.model;

import lombok.Data;

@Data
public class Room {

    private final int[][] map;

    public Room(int length, int width) {
        map = new int[length][width];
    }
}
