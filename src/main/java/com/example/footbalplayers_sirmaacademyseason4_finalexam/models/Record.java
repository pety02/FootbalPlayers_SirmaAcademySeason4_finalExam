package com.example.footbalplayers_sirmaacademyseason4_finalexam.models;

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
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Player player;
    @ManyToOne
    private Match match;
    @Column(nullable = false)
    private Integer fromMinutes;
    @Column
    private Integer toMinutes;
}