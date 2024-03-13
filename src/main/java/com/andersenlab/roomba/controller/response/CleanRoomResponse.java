package com.andersenlab.roomba.controller.response;

import com.andersenlab.roomba.controller.request.CleanerCoordsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CleanRoomResponse {

    private CleanerCoordsDto coords;
    private int patches;
}
