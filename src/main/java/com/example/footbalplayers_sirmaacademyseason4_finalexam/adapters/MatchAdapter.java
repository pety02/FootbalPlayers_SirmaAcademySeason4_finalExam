package com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters.interfaces.Adaptable;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.MatchDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.TeamDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Match;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchAdapter implements Adaptable<Match, MatchDTO> {
    private final TeamRepository teamRepository;
    private final TeamAdapter teamConverter;

    /**
     * MatchConverter class constructor with arguments
     * @param teamRepository the team repository
     * @param teamConverter the team converter
     */
    @Autowired
    public MatchAdapter(TeamRepository teamRepository,
                        TeamAdapter teamConverter) {
        this.teamRepository = teamRepository;
        this.teamConverter = teamConverter;
    }
    /**
     * Converts a MatchDTO object to a Match object
     * @param matchDTO the MatchDTO object
     * @return a Match object
     */
    @Override
    public Match toEntity(MatchDTO matchDTO) {
        if(matchDTO == null) {
            return null;
        }
        Match match = new Match();
        match.setId(matchDTO.getId());
        Team teamA = teamRepository.findById(matchDTO.getATeamId()).orElse(null);
        match.setATeam(teamA);
        Team teamB = teamRepository.findById(matchDTO.getBTeamId()).orElse(null);
        match.setBTeam(teamB);
        match.setDate(matchDTO.getDate());
        match.setScore(matchDTO.getScore());

        return match;
    }

    /**
     * Converts a Match object to a MatchDTO object
     * @param match the Match object
     * @return a MatchDTO object
     */
    @Override
    public MatchDTO toDTO(Match match) {
        if(match == null) {
            return null;
        }
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setId(match.getId());
        matchDTO.setATeamId(match.getATeam().getId());
        matchDTO.setBTeamId(match.getBTeam().getId());
        matchDTO.setDate(match.getDate());
        matchDTO.setScore(match.getScore());

        return matchDTO;
    }
}