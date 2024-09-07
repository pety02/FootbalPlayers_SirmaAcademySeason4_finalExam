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
@Table(name = "TEAMS")
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "ManagerFullName", nullable = false, length = 150)
    private String managerFullName;
    @Column(name = "TeamGroup", nullable = false, length = 1)
    private String group;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Player> players;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Match> matches;
}