package com.example.footbalplayers_sirmaacademyseason4_finalexam.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class StatisticsController {
    @GetMapping("/players-in-one-match")
    public String getStatistics() {
        return "statistics";
    }
}