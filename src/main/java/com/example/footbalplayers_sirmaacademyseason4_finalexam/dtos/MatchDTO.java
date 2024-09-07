package com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

// TODO: to add spring validation annotations
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MatchDTO {
    private Long id;
    private Long aTeamId;
    private Long bTeamId;
    private LocalDate date;
    private String score;
}