package com.example.footbalplayers_sirmaacademyseason4_finalexam.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Date;
import java.time.LocalDate;

@Converter(autoApply = true)
public class DatesConverter implements AttributeConverter<LocalDate, Date> {

    /**
     * Converts java.time.LocalDate to java.sql.Date
     * @param localDate the java.time.LocalDate objects that should be converted to java.sql.Date
     * @return null if the java.time.LocalDate object is null or the converted date
     */
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return localDate == null ? null : Date.valueOf(localDate);
    }

    /**
     * Converts java.sql.Date to java.time.LocalDate
     * @param date the java.sql.Date objects that should be converted to java.time.LocalDate
     * @return null if the java.sql.Date object is null or the converted date
     */
    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return date == null ? null : date.toLocalDate();
    }
}