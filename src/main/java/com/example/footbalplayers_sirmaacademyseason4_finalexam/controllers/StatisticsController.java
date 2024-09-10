package com.example.footbalplayers_sirmaacademyseason4_finalexam.controllers;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Validated
@Slf4j
public class StatisticsController {
    @GetMapping("/players-in-one-match")
    public String getStatistics(@NonNull Model model) {

        return "statistics";
    }
}