package com.andersenlab.roomba.controller.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RoomSizeDto {

    @Min(value = 0, message = "Length of room should start from 0")
    private int length;

    @Min(value = 0, message = "Width of room should start from 0")
    private int width;
}
