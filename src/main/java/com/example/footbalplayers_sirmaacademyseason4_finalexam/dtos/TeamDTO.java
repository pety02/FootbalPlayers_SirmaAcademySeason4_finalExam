package com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos;

import lombok.*;

import java.util.List;

// TODO: to add spring validation annotations
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TeamDTO {
    private Long id;
    private String name;
    private String managerFullName;
    private String group;
    private List<Long> playersIds;
    private List<Long> matchesIds;
}