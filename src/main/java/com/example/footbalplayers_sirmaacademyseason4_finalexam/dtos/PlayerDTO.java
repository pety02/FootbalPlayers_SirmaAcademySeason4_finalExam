package com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

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