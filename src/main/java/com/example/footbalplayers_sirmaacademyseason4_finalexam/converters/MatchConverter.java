package com.example.footbalplayers_sirmaacademyseason4_finalexam.converters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.converters.interfaces.Convertable;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.MatchDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Match;
import org.springframework.stereotype.Component;

// TODO: to comment the class
@Component
public class MatchConverter implements Convertable<Match, MatchDTO> {
    /**
     *
     * @param matchDTO
     * @return
     */
    @Override
    public Match toEntity(MatchDTO matchDTO) {
        // TODO: to implement it
        return null;
    }

    /**
     *
     * @param match
     * @return
     */
    @Override
    public MatchDTO toDTO(Match match) {
        // TODO: to implement it
        return null;
    }
}