package com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SupportingTableDTO {
    @Min(value = 1, message = "FirstID should be positive number!")
    Long firstId;
    @Min(value = 1, message = "SecondID should be positive number!")
    Long secondId;
}