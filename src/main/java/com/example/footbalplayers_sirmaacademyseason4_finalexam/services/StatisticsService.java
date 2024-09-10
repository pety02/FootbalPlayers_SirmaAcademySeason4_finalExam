package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.StatisticsDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.implementations.StatisticsRepositoryImpl;
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

    /**
     * StatisticsService class constructor with a parameter
     * @param statisticsRepository the statistics repository object
     */
    @Autowired
    public StatisticsService(@NonNull StatisticsRepositoryImpl statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    /**
     * Loads all match of players that are from different teams and are played together
     * for the longest period of time with the duration of their common play as a List of
     * StatisticsDTO objects
     * @return a List of StatisticsDTO objects
     */
    public List<StatisticsDTO> loadAll() {
        return statisticsRepository.loadAll();
    }
}