package com.example.footbalplayers_sirmaacademyseason4_finalexam.controllers;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.TeamDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.TeamService;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.CSVDataExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class MainController {
    private final TeamService teamService;

    @Autowired
    public MainController(TeamService teamService) {
        this.teamService = teamService;
    }
    // TODO: this is the way I will populate the database on the first execution
    // TODO: to debug and find the problem for this message: Exception occurred:Invalid headers!
    public void populateDataBase() {
        final CSVDataExtractor dataExtractor = new CSVDataExtractor();
        final String teamCSVFilename = "C:\\Users\\User\\OneDrive\\Documents\\FootbalPlayers_SirmaAcademySeason4_finalExam\\src\\main\\resources\\static\\teams.csv";

        List<TeamDTO> teamDTOs = dataExtractor.extractTeams(teamCSVFilename);
        for(TeamDTO teamDTO : teamDTOs) {
            teamService.create(teamDTO);
        }
    }
    @GetMapping("/")
    public String getHomePage() {
        populateDataBase();
        return "home";
    }
}