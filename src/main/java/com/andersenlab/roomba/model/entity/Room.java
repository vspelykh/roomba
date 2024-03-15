package com.andersenlab.roomba.model.entity;

import com.andersenlab.roomba.model.enumeration.CellType;
import com.andersenlab.roomba.model.request.PatchDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class Room {

    private final List<Cell> cells;
    private int length;
    private int width;

    public Room(int length, int width, List<PatchDto> patchDtos) {
        this.length = length;
        this.width = width;
        cells = new ArrayList<>(length * width);
        populateRoomWithEmptyCells(length, width);
        putPatchesInRoom(patchDtos);
    }

    public Cell getCellByCoords(int x, int y) {
        Optional<Cell> optionalCell = cells.stream().filter(cell -> cell.getX() == x && cell.getY() == y).findFirst();
        if (optionalCell.isPresent()) {
            return optionalCell.get();
        } else {
            Cell cell = new Cell(x, y, CellType.EMPTY);
            cells.add(cell);
            return cell;
        }
    }

    private void populateRoomWithEmptyCells(int length, int width) {
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                cells.add(new Cell(x, y, CellType.EMPTY));
            }
        }
    }

    private void putPatchesInRoom(List<PatchDto> patchDtos) {
        patchDtos.forEach(this::putPatchInRoom);
    }

    private void putPatchInRoom(PatchDto patchDto) {
        Optional<Cell> optionalCell =
                cells.stream()
                        .filter(cell -> cell.getX() == patchDto.getX() && cell.getY() == patchDto.getY()).
                        findFirst();

        if (optionalCell.isPresent()) {
            Cell cellToPutPatch = optionalCell.get();
            cellToPutPatch.setType(CellType.PATCH);
        } else {
            cells.add(new Cell(patchDto.getX(), patchDto.getY(), CellType.PATCH));
        }
    }
}