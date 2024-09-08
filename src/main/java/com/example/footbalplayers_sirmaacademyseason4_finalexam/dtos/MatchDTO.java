package com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MatchDTO {
    private Long id;
    @NotNull(message = "ATeamId should not be null!")
    @Min(value = 1, message = "ATeamId should be positive number!")
    private Long aTeamId;
    @NotNull(message = "BTeamId should not be null!")
    @Min(value = 1, message = "BTeamId should be positive number!")
    private Long bTeamId;
    @NotNull(message = "Date should not be null!")
    // TODO: after adding custom valid date annotation and custom date validator to annotate it properly
    private LocalDate date;
    @NotNull(message = "Score should not be null!")
    @NotBlank(message = "Score should not be blank!")
    @Size(min = 3, max = 9)
    @Pattern(regexp = "^\\d(\\(\\d\\))?-\\d(\\(\\d\\))?$", message = "Score should matches digit-digit!")
    private String score;
}