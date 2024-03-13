package com.andersenlab.roomba.service;

import com.andersenlab.roomba.controller.request.CleanRoomRequest;
import com.andersenlab.roomba.controller.request.CleanerCoordsDto;
import com.andersenlab.roomba.controller.request.PatchDto;
import com.andersenlab.roomba.controller.request.RoomSizeDto;
import com.andersenlab.roomba.controller.response.CleanRoomResponse;
import com.andersenlab.roomba.model.Instruction;
import com.andersenlab.roomba.model.Room;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CleanerServiceImpl implements CleanerService {

    @Override
    public CleanRoomResponse cleanRoom(CleanRoomRequest cleanRoomRequest) {
        Room room = buildRoom(cleanRoomRequest.getRoomSize());
        setPatchesInHouse(room, cleanRoomRequest.getPatches());
        List<Instruction> instructions = parseInstructions(cleanRoomRequest.getInstructions());
        return conductCleaningRoom(room, cleanRoomRequest.getCoords(), instructions);
    }

    private Room buildRoom(RoomSizeDto roomSize) {
        return new Room(roomSize.getLength(), roomSize.getWidth());
    }

    private void setPatchesInHouse(Room room, List<PatchDto> patches) {
        patches.stream()
                .filter(patch -> patch.getX() < room.getMap().length && patch.getY() < room.getMap()[0].length)
                .forEach(patch -> room.getMap()[patch.getY()][patch.getX()] = 1);
    }

    private List<Instruction> parseInstructions(String instructions) {
        return Arrays.stream(instructions.split("")).map(Instruction::valueOf).toList();
    }

    private CleanRoomResponse conductCleaningRoom(Room room, CleanerCoordsDto coords, List<Instruction> instructions) {
        int patchesCount = 0;
        for (Instruction instruction : instructions) {
            validateMoveAndSetCoords(instruction, coords, room);
            patchesCount = countAndRemoveIfPatchExists(patchesCount, coords, room.getMap());
        }
        return new CleanRoomResponse(coords, patchesCount);
    }

    private int countAndRemoveIfPatchExists(int patchesCount, CleanerCoordsDto coords, int[][] map) {
        if (map[coords.getY()][coords.getX()] == 1) {
            map[coords.getY()][coords.getX()] = 0;
            patchesCount++;
        }
        return patchesCount;
    }

    private void validateMoveAndSetCoords(Instruction instruction, CleanerCoordsDto coords, Room room) {
        switch (instruction) {
            case N -> {
                if (coords.getY() + 1 < room.getMap()[0].length) {
                    coords.setY(coords.getY() + 1);
                }
            }
            case S -> {
                if (coords.getY() - 1 >= 0) {
                    coords.setY(coords.getY() - 1);
                }
            }
            case E -> {
                if (coords.getX() + 1 < room.getMap().length) {
                    coords.setX(coords.getX() + 1);
                }
            }
            case W -> {
                if (coords.getX() - 1 >= 0) {
                    coords.setX(coords.getX() - 1);
                }
            }
        }
    }
}
