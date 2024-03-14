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
public class CleanerCoordsDto {

    @Min(value = 0, message = "X coordinate of the cleaner should start from 0")
    private int x;

    @Min(value = 0, message = "Y coordinate of the cleaner should start from 0")
    private int y;
}
