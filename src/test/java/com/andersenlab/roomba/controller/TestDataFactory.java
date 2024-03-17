package com.andersenlab.roomba.controller;

import com.andersenlab.roomba.model.entity.CleaningResult;
import com.andersenlab.roomba.model.request.CleanRoomRequest;
import com.andersenlab.roomba.model.request.HooverCoordsDto;
import com.andersenlab.roomba.model.request.PatchDto;
import com.andersenlab.roomba.model.request.RoomSizeDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataFactory {

    static CleanRoomRequest getRequest() {
        return CleanRoomRequest.builder()
                .roomSize(new RoomSizeDto(5, 5))
                .coords(new HooverCoordsDto(0, 0))
                .patches(List.of(new PatchDto(1, 1)))
                .instructions("NEEES")
                .build();
    }

    static CleaningResult getExpectedResult() {
        CleaningResult cleaningResult = new CleaningResult(3, 0, 1);
        cleaningResult.setId(1);
        return cleaningResult;
    }
}
