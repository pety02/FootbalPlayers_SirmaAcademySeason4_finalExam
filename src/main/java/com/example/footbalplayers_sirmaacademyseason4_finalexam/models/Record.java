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
@Table(name = "RECORDS")
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "PlayerID", nullable = false)
    private Player player;
    @ManyToOne
    @JoinColumn(name = "MatchID", nullable = false)
    private Match match;
    @Column(name = "FromMinutes", nullable = false)
    private Integer fromMinutes;
    @Column(name = "ToMinutes")
    private Integer toMinutes;
}