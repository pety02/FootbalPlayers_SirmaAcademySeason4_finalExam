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
public class RecordDTO {
    private Long id;
    @NotNull(message = "PlayerID should not be null!")
    @Min(value = 1, message = "PlayerID should be positive number!")
    private Long playerId;
    @NotNull(message = "MatchID should not be null!")
    @Min(value = 1, message = "MatchID should be positive number!")
    private Long matchId;
    @NotNull(message = "FromMinutes should not be null!")
    @Min(value = 1, message = "FromMinutes should be positive number!")
    private Integer fromMinutes;
    @Min(value = 1, message = "ToMinutes should be positive number!")
    private Integer toMinutes;
}