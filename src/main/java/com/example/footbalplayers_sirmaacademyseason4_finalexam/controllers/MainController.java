package com.example.footbalplayers_sirmaacademyseason4_finalexam.controllers;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.MatchDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.PlayerDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.RecordDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.TeamDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.*;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.CSVDataExtractor;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
@Slf4j
@Validated
public class MainController {
    private final CSVDataExtractor dataExtractor = new CSVDataExtractor();
    private final String matchCSVFilename = "C:\\Users\\User\\OneDrive\\Documents\\FootbalPlayers_SirmaAcademySeason4_finalExam\\src\\main\\resources\\static\\matches.csv";
    private final String playerCSVFilename = "C:\\Users\\User\\OneDrive\\Documents\\FootbalPlayers_SirmaAcademySeason4_finalExam\\src\\main\\resources\\static\\players.csv";
    private final String teamCSVFilename = "C:\\Users\\User\\OneDrive\\Documents\\FootbalPlayers_SirmaAcademySeason4_finalExam\\src\\main\\resources\\static\\teams.csv";
    private final String recordCSVFilename = "C:\\Users\\User\\OneDrive\\Documents\\FootbalPlayers_SirmaAcademySeason4_finalExam\\src\\main\\resources\\static\\records.csv";
    private final MatchService matchService;
    private final PlayerService playerService;
    private final TeamService teamService;
    private final RecordService recordService;
    private final EmptyDataBaseService emptyDataBaseService;

    private void populateMatches() {
        List<MatchDTO> matchDTOs = dataExtractor.extractMatches(matchCSVFilename);
        for(@Valid MatchDTO matchDTO : matchDTOs) {
            matchService.create(matchDTO);
        }
    }

    private void populatePlayers() {
        List<PlayerDTO> playerDTOs = dataExtractor.extractPlayers(playerCSVFilename);
        for(@Valid PlayerDTO playerDTO : playerDTOs) {
            playerService.create(playerDTO);
        }
    }

    private void populateTeams() {
        List<TeamDTO> teamDTOs = dataExtractor.extractTeams(teamCSVFilename);
        for(@Valid TeamDTO teamDTO : teamDTOs) {
            teamService.create(teamDTO);
        }
    }

    private void populateRecords() {
        List<RecordDTO> recordDTOs = dataExtractor.extractRecords(recordCSVFilename);
        for(@Valid RecordDTO recordDTO : recordDTOs) {
            recordService.create(recordDTO);
        }
    }

    @Autowired
    public MainController(@NonNull MatchService matchService,
                          @NonNull PlayerService playerService,
                          @NonNull TeamService teamService,
                          @NonNull RecordService recordService,
                          @NonNull EmptyDataBaseService emptyDataBaseService) {
        this.matchService = matchService;
        this.playerService = playerService;
        this.teamService = teamService;
        this.recordService = recordService;
        this.emptyDataBaseService = emptyDataBaseService;
    }

    public void populateDataBase() {
        if(emptyDataBaseService.isEmpty()) {
            populateTeams();
            populatePlayers();
            populateMatches();
            populateRecords();
        }
    }
    @GetMapping("/")
    public String getHomePage() {
        populateDataBase();
        return "home";
    }
}