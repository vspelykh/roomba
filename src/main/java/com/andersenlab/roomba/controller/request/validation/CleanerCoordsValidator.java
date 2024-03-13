package com.andersenlab.roomba.controller.request.validation;

import com.andersenlab.roomba.controller.request.CleanRoomRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CleanerCoordsValidator implements ConstraintValidator<ValidCleanerCoords, CleanRoomRequest> {

    @Override
    public boolean isValid(CleanRoomRequest request, ConstraintValidatorContext context) {
        if (request == null || request.getCoords() == null) {
            return false;
        }

        int roomWidth = request.getRoomSize().getWidth();
        int roomLength = request.getRoomSize().getLength();

        int cleanerX = request.getCoords().getX();
        int cleanerY = request.getCoords().getY();

        return cleanerX >= 0 && cleanerX < roomWidth && cleanerY >= 0 && cleanerY < roomLength;
    }
}
