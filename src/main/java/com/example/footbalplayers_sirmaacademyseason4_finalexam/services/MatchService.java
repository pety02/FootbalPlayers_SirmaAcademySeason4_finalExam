package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.converters.MatchConverter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.MatchDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Match;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.MatchRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class MatchService implements Service<Match, MatchDTO> {
    private final MatchRepository matchRepository;
    private final MatchConverter matchConverter;

    /**
     * MatchService class constructor with arguments
     * @param matchRepository the match repository
     * @param matchConverter the match converter
     */
    @Autowired
    public MatchService(MatchRepository matchRepository,
                        MatchConverter matchConverter) {
        this.matchRepository = matchRepository;
        this.matchConverter = matchConverter;
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
        // TODO: cascade creation
        Match match = matchConverter.toEntity(dto);
        return matchConverter.toDTO(matchRepository.save(match));
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
        // TODO: cascade updation
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
        // TODO: cascade deletion
        matchRepository.deleteById(id);
    }
}