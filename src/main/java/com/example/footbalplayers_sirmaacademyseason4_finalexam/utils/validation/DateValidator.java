package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.validation;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.annotations.ValidDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateValidator implements ConstraintValidator<ValidDate, LocalDate> {
    private String[] formats;

    /**
     * DateValidator class constructor with parameters
     * @param constraintAnnotation ValidDate annotation object
     */
    @Override
    public void initialize (ValidDate constraintAnnotation)
    {
        this.formats = constraintAnnotation.formats();
    }

    /**
     * Validates the format of the date
     * @param format the format
     * @param value the value
     * @return true if the value is in valid format and false if not
     */
    private boolean isValidFormat(String format, String value) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            LocalDate date = LocalDate.parse(value);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    /**
     * Validates the LocalDate object
     * @param localDate the LocalDate object
     * @param constraintValidatorContext the ConstraintValidatorContext object
     *                                   usually used for customizing the error
     *                                   message or disabling default constraint
     *                                   violations
     * @return true if the date is in correct format and false if not
     */
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if(localDate == null) {
            return false;
        }
        for (String format : this.formats) {
            if (isValidFormat(format, localDate.toString())) {
                return true;
            }
        }

        return false;
    }
}