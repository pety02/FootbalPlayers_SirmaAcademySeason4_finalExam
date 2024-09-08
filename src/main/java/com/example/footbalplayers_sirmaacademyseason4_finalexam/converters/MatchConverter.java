package com.example.footbalplayers_sirmaacademyseason4_finalexam.converters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.converters.interfaces.Convertable;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.MatchDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.TeamDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Match;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchConverter implements Convertable<Match, MatchDTO> {
    private final TeamRepository teamRepository;
    private final TeamConverter teamConverter;

    /**
     * MatchConverter class constructor with arguments
     * @param teamRepository the team repository
     * @param teamConverter the team converter
     */
    @Autowired
    public MatchConverter(TeamRepository teamRepository,
                          TeamConverter teamConverter) {
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
        TeamDTO teamADTO = teamConverter.toDTO(match.getATeam());
        matchDTO.setATeamId(teamADTO.getId());
        TeamDTO teamBDTO = teamConverter.toDTO(match.getBTeam());
        matchDTO.setBTeamId(teamBDTO.getId());
        matchDTO.setDate(match.getDate());
        matchDTO.setScore(match.getScore());

        return matchDTO;
    }
}