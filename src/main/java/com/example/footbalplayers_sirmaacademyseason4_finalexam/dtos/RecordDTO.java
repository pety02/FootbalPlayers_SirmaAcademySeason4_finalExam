package com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// TODO: to add spring validation annotations
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecordDTO {
    private Long id;
    private Long playerId;
    private Long matchId;
    private Integer fromMinutes;
    private Integer toMinutes;
}