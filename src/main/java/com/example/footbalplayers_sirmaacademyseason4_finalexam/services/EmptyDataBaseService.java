package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.EmptyDataBaseRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class EmptyDataBaseService {
    private final EmptyDataBaseRepository emptyDataBaseRepository;

    /**
     * EmptyDataBseService class constructor with a parameter
     * @param emptyDataBaseRepository the empty database repository
     */
    @Autowired
    public EmptyDataBaseService(@NonNull EmptyDataBaseRepository emptyDataBaseRepository) {
        this.emptyDataBaseRepository = emptyDataBaseRepository;
    }

    /**
     * Checks if the database is empty
     * @return true if the database is empty and false if not
     */
    public boolean isEmpty() {
        return emptyDataBaseRepository.countPlayerRecords() == 0
                && emptyDataBaseRepository.countTeamMatches() == 0
                && emptyDataBaseRepository.countTeamPlayers() == 0;
    }
}