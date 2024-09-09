package com.example.footbalplayers_sirmaacademyseason4_finalexam.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, length = 150)
    private String managerFullName;
    @Column(nullable = false, length = 1)
    private String teamGroup;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Player> players;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Match> matches;
}