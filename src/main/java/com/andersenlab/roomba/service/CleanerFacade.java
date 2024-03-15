package com.andersenlab.roomba.service;

import com.andersenlab.roomba.model.entity.CleaningResult;
import com.andersenlab.roomba.model.entity.Hoover;
import com.andersenlab.roomba.model.entity.Room;
import com.andersenlab.roomba.model.enumeration.Instruction;
import com.andersenlab.roomba.model.request.CleanRoomRequest;
import com.andersenlab.roomba.model.request.HooverCoordsDto;
import com.andersenlab.roomba.model.response.CleanRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CleanerFacade {

    private final RoomService roomService;
    private final HooverService hooverService;
    private final CleaningResultService cleaningResultService;

    public CleanRoomResponse cleanRoom(CleanRoomRequest cleanRoomRequest) {
        Room room = roomService.createRoomWithPatches(cleanRoomRequest.getRoomSize(), cleanRoomRequest.getPatches());
        List<Instruction> instructions = hooverService.parseInstructions(cleanRoomRequest.getInstructions());
        Hoover hoover = hooverService.getHoover(cleanRoomRequest.getCoords(), room);

        CleaningResult cleaningResult = hoover.conductCleaningRoom(instructions);
        cleaningResult = cleaningResultService.save(cleaningResult);

        return CleanRoomResponse.builder()
                .coords(new HooverCoordsDto(cleaningResult.getCoordinateX(), cleaningResult.getCoordinateY()))
                .patches(cleaningResult.getPatches())
                .build();
    }
}
