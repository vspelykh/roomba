package com.andersenlab.roomba.model.request;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestConstants {
    static final int MIN_COORDINATE_VALUE = 0;

    static final String INSTRUCTIONS_PATTERN = "[NSEW]+";
}
