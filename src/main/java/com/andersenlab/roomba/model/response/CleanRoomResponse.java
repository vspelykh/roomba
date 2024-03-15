package com.andersenlab.roomba.model.response;

import com.andersenlab.roomba.model.request.HooverCoordsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CleanRoomResponse {

    private HooverCoordsDto coords;
    private int patches;
}
