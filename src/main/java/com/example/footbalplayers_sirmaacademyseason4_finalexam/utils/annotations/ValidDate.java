package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.annotations;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.validation.DateValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDate {
    String message();
    String[] formats();
}