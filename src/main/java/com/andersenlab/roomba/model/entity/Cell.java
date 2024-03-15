package com.andersenlab.roomba.model.entity;

import com.andersenlab.roomba.model.enumeration.CellType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cell {

    private int x;
    private int y;
    private CellType type;
}
