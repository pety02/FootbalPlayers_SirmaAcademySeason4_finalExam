package com.example.footbalplayers_sirmaacademyseason4_finalexam.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table
@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Team aTeam;
    @ManyToOne(fetch = FetchType.EAGER)
    private Team bTeam;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String score;
}