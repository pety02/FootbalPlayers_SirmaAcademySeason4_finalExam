package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters.MatchAdapter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.MatchDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.SupportingTableDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Match;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.MatchRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces.Service;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces.SupportingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class MatchService implements Service<Match, MatchDTO> {
    private final MatchRepository matchRepository;
    private final MatchAdapter matchConverter;
    private final SupportingService supportingService;

    /**
     * MatchService class constructor with arguments
     * @param matchRepository the match repository
     * @param matchConverter the match converter
     * @param supportingService the supporting tables service
     */
    @Autowired
    public MatchService(MatchRepository matchRepository,
                        MatchAdapter matchConverter,
                        SupportingService supportingService) {
        this.matchRepository = matchRepository;
        this.matchConverter = matchConverter;
        this.supportingService = supportingService;
    }

    /**
     * Loads a Match object from the database by its id and converts it to MatchDTO object
     * @param id the definite id
     * @return a MatchDTO object
     */
    @Override
    public MatchDTO loadByID(Long id) {
        return matchConverter.toDTO(matchRepository.findById(id).orElse(null));
    }

    /**
     * Loads all Match records from the database and converts them to MatchDTO objects
     * @return a List of MatchDTO objects
     */
    @Override
    public List<MatchDTO> loadAll() {
        List<Match> matches = matchRepository.findAll();
        List<MatchDTO> matchDTOs = new ArrayList<>();
        for(Match match : matches) {
            matchDTOs.add(matchConverter.toDTO(match));
        }

        return matchDTOs;
    }

    /**
     * Creates Match object from a MatchDTO object and store it in the database
     * @param dto the MatchDTO object
     * @return the saved Match object converted to a MatchDTO object
     */
    @Override
    public MatchDTO create(MatchDTO dto) {
        Match created = matchRepository.save(matchConverter.toEntity(dto));
        SupportingTableDTO teamMatchDTO = new SupportingTableDTO(created.getATeam().getId(), created.getId());
        supportingService.create(Team.class, Match.class, teamMatchDTO);
        return matchConverter.toDTO(created);
    }

    /**
     * Updates a definite Match object via its id in the database,
     * converts it to the MatchDTO object and return the converted
     * object
     * @param id the definite id
     * @param dto the updated values for the Match object packaged in
     *            the MatchDTO object
     */
    @Override
    public void update(Long id, MatchDTO dto) {
        if(matchRepository.existsById(id) && dto.getId().equals(id)) {
            Match match = matchConverter.toEntity(dto);
            matchRepository.save(match);
        }
    }

    /**
     * Deletes a definite Match object via its id from the database
     * @param id the definite Match object's id
     */
    @Override
    public void deleteById(Long id) {
        if(matchRepository.findById(id).isPresent()) {
            Match match = matchRepository.findById(id).get();
            supportingService.deleteById(Team.class, Match.class, match.getATeam().getId(), match.getId());
            supportingService.deleteById(Team.class, Match.class, match.getBTeam().getId(), match.getId());
            matchRepository.deleteById(id);
        }
    }
}