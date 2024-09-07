package com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos;

import lombok.*;

import java.util.List;

// TODO: to add spring validation annotations
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlayerDTO {
    private Long id;
    private Integer teamNumber;
    private String position;
    private String fullName;
    private Long teamId;
    private List<Long> recordsIds;
}