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
    @Autowired
    public StatisticsController(@NonNull StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/players-in-one-match")
    public String getStatistics(@NonNull Model model) {
        model.addAttribute("playersOverlaps", statisticsService.loadAll());
        return "statistics";
    }
}