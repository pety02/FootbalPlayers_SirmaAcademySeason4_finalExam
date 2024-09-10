package com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos;

import jakarta.validation.constraints.Max;
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
public class RecordDTO {
    private Long id;
    @NotNull(message = "PlayerID should not be null!")
    @Min(value = 1, message = "PlayerID should be positive number!")
    private Long playerId;
    @NotNull(message = "MatchID should not be null!")
    @Min(value = 1, message = "MatchID should be positive number!")
    private Long matchId;
    @NotNull(message = "FromMinutes should not be null!")
    @Min(value = 0, message = "FromMinutes should be positive number!")
    @Max(value = 90, message = "FromMinutes should be less than 90!")
    private Integer fromMinutes;
    @Max(value = 90, message = "ToMinutes should be less than 90!")
    private Integer toMinutes;
}