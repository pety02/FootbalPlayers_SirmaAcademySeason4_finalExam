package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.annotations;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.validation.DateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface ValidDate {
    String[] formats();
    String message() default "Invalid date format!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}