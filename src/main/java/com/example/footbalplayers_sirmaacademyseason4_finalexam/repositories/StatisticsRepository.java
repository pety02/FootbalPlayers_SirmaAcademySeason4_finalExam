package com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.StatisticsDTO;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StatisticsRepository {
    List<StatisticsDTO> loadAll();
}