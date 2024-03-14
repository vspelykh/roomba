package com.andersenlab.roomba.model.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.andersenlab.roomba.model.request.RequestConstants.MIN_COORDINATE_VALUE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PatchDto {

    @Min(value = MIN_COORDINATE_VALUE, message = "X coordinate of the patch should start from 0")
    private int x;

    @Min(value = MIN_COORDINATE_VALUE, message = "X coordinate of the patch should start from 0")
    private int y;
}
