package com.example.footbalplayers_sirmaacademyseason4_finalexam.controllers;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.StatisticsService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Validated
@Slf4j
public class StatisticsController {
    private final StatisticsService statisticsService;

    /**
     * StatisticsController class constructor with a parameter
     * @param statisticsService the statistics service
     */
    @Autowired
    public StatisticsController(@NonNull StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    /**
     * Gets all players that are from different teams and are played together in the same
     * match for the longest period of time in minutes and the duration of their common play
     * @param model the Model object to which the List of this players will be attached
     * @return the statistics.html view that shows the user these players in a table view
     */
    @GetMapping("/players-in-one-match")
    public String getStatistics(@NonNull Model model) {
        model.addAttribute("playersOverlaps", statisticsService.loadAll());
        return "statistics";
    }
}