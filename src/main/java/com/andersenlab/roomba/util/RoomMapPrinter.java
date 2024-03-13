package com.andersenlab.roomba.util;

import com.andersenlab.roomba.controller.request.CleanerCoordsDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomMapPrinter {

    public static void printHouse(int[][] map, CleanerCoordsDto coords) {
        for (int y = map.length - 1; y >= 0; y--) {
            for (int x = 0; x < map[y].length; x++) {
                if (coords.getX() == x && coords.getY() == y) {
                    System.out.print("R");
                } else {
                    System.out.print(map[y][x]);
                }
            }
            System.out.println();
        }
    }
}
