package com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StatisticsDTO {
    private Long matchId;
    private String playerOfTeamAFullName;
    private String playerOfTeamBFullName;
    private Integer duration;
}