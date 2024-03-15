package com.andersenlab.roomba.service;

import com.andersenlab.roomba.model.entity.Hoover;
import com.andersenlab.roomba.model.entity.Room;
import com.andersenlab.roomba.model.enumeration.Instruction;
import com.andersenlab.roomba.model.request.HooverCoordsDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class HooverService {

    public Hoover getHoover(HooverCoordsDto hooverCoordsDto, Room room) {
        return new Hoover(hooverCoordsDto.getX(), hooverCoordsDto.getY(), room);
    }

    public List<Instruction> parseInstructions(String instructions) {
        return Arrays.stream(instructions.split("")).map(Instruction::valueOf).toList();
    }
}
