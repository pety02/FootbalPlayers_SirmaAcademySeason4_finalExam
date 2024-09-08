package com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlayerDTO {
    private Long id;
    @NotNull(message = "TeamNumber should not be null!")
    @Min(value = 1, message = "TeamNumber should be positive number!")
    private Integer teamNumber;
    @NotNull(message = "Position should not be null!")
    @NotBlank(message = "Position should not be blank!")
    @Size(min = 2, max = 2, message = "Position should have 2 symbols length!")
    @Pattern(regexp = "^GK|DF|MF|FW$", message = "Position should be GK, DF, MF or FW!")
    private String position;
    @NotNull(message = "FullName should not be null!")
    @NotBlank(message = "FullName should not be blank!")
    @Size(min = 1, max = 150, message = "FullName should have 2 symbols length!")
    @Pattern(regexp = "^[A-Z]{1}[a-z]{1,} [A-Z]{1}[a-z]{1,}$",
            message = "First name and surname should start with a capital letter and continue with lowercase!")
    private String fullName;
    @NotNull(message = "TeamID should not be null!")
    @Min(value = 1, message = "TeamID should be positive number!")
    private Long teamId;
    @NotNull(message = "RecordsIds should not be null!")
    private List<Long> recordsIds;
}