package com.andersenlab.roomba.controller;

import com.andersenlab.roomba.controller.request.CleanRoomRequest;
import com.andersenlab.roomba.controller.request.CleanerCoordsDto;
import com.andersenlab.roomba.controller.request.PatchDto;
import com.andersenlab.roomba.controller.request.RoomSizeDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataFactory {

    static CleanRoomRequest getRequest() {
        return CleanRoomRequest.builder()
                .roomSize(new RoomSizeDto(5, 5))
                .coords(new CleanerCoordsDto(0, 0))
                .patches(List.of(new PatchDto(1, 1)))
                .instructions("NEEES")
                .build();
    }
}
