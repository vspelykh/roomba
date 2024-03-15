package com.andersenlab.roomba.service;

import com.andersenlab.roomba.model.entity.Room;
import com.andersenlab.roomba.model.request.PatchDto;
import com.andersenlab.roomba.model.request.RoomSizeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    public Room createRoomWithPatches(RoomSizeDto roomSize, List<PatchDto> patchDtos) {
        return new Room(roomSize.getLength(), roomSize.getWidth(), patchDtos);
    }
}
