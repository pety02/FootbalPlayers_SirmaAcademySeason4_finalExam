package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.StatisticsDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.StatisticsRepositoryImpl;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Slf4j
@Validated
public class StatisticsService {
    private final StatisticsRepositoryImpl statisticsRepository;

    @Autowired
    public StatisticsService(@NonNull StatisticsRepositoryImpl statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public List<StatisticsDTO> loadAll() {
        return statisticsRepository.loadAll();
    }
}