package com.andersenlab.roomba.controller.request.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CleanerCoordsValidator.class)
public @interface ValidCleanerCoords {
    String message() default "Invalid cleaner coordinates";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}