package com.example.footbalplayers_sirmaacademyseason4_finalexam.models;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer teamNumber;
    @Column(nullable = false, length = 2)
    private String position;
    @Column(nullable = false, length = 150)
    private String fullName;
    @ManyToOne
    private Team team;
    @OneToMany
    private List<Record> records;
}