package com.andersenlab.roomba.model.request;

import com.andersenlab.roomba.model.request.validation.ValidCleanerCoords;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.andersenlab.roomba.model.request.RequestConstants.INSTRUCTIONS_PATTERN;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidCleanerCoords
public class CleanRoomRequest {

    @Valid
    private RoomSizeDto roomSize;

    @Valid
    private HooverCoordsDto coords;

    @Valid
    private List<PatchDto> patches;

    @Pattern(regexp = INSTRUCTIONS_PATTERN, message = "Instruction contains an invalid command")
    private String instructions;
}
