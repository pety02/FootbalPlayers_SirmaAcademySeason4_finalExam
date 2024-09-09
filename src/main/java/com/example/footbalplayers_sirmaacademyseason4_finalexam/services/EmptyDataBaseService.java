package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.EmptyDataBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmptyDataBaseService {
    private final EmptyDataBaseRepository emptyDataBaseRepository;
    @Autowired
    public EmptyDataBaseService(EmptyDataBaseRepository emptyDataBaseRepository) {
        this.emptyDataBaseRepository = emptyDataBaseRepository;
    }

    public boolean isEmpty() {
        return emptyDataBaseRepository.countPlayerRecords() == 0
                && emptyDataBaseRepository.countTeamMatches() == 0
                && emptyDataBaseRepository.countTeamPlayers() == 0;
    }
}