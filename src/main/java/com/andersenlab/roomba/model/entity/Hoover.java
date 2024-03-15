package com.andersenlab.roomba.model.entity;

import com.andersenlab.roomba.model.enumeration.CellType;
import com.andersenlab.roomba.model.enumeration.Instruction;
import lombok.Data;

import java.util.List;

@Data
public class Hoover {

    private int x;
    private int y;
    private Room room;
    private int patchesCount;

    public Hoover(int x, int y, Room room) {
        this.x = x;
        this.y = y;
        this.room = room;
    }

    public void validateMoveAndSetCoords(Instruction instruction) {
        switch (instruction) {
            case N -> {
                if (getY() + 1 < room.getLength()) {
                    setY(y + 1);
                }
            }
            case S -> {
                if (getY() - 1 >= 0) {
                    setY(getY() - 1);
                }
            }
            case E -> {
                if (getX() + 1 < room.getWidth()) {
                    setX(getX() + 1);
                }
            }
            case W -> {
                if (getX() - 1 >= 0) {
                    setX(getX() - 1);
                }
            }
        }
    }

    public CleaningResult conductCleaningRoom(List<Instruction> instructions) {
        instructions.forEach(instruction -> {
            validateMoveAndSetCoords(instruction);
            countAndRemoveIfPatchExists();
        });
        return new CleaningResult(getX(), getY(), patchesCount);
    }

    private void countAndRemoveIfPatchExists() {
        Cell cellByCoords = room.getCellByCoords(x, y);
        if (cellByCoords.getType() == CellType.PATCH) {
            cellByCoords.setType(CellType.EMPTY);
            patchesCount++;
        }
    }
}
