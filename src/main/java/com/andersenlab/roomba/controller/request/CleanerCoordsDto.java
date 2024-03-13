package com.andersenlab.roomba.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CleanerCoordsDto {

    @Range(min = 0)
    private int x;

    @Range(min = 0)
    private int y;
}
