package com.andersenlab.roomba.controller.request;

import com.andersenlab.roomba.controller.request.validation.ValidCleanerCoords;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidCleanerCoords
public class CleanRoomRequest {

    @Valid
    private RoomSizeDto roomSize;

    @Valid
    private CleanerCoordsDto coords;

    @Valid
    private List<PatchDto> patches;

    @Pattern(regexp = "[NSEW]+")
    private String instructions;
}
