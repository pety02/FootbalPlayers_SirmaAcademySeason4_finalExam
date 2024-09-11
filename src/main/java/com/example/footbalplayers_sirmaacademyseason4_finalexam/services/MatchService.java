package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters.MatchAdapter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.MatchDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.SupportingTableDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Match;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.MatchRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces.Service;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces.SupportingService;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
@Validated
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
    public MatchService(@NonNull MatchRepository matchRepository,
                        @NonNull MatchAdapter matchConverter,
                        @NonNull SupportingService supportingService) {
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
     * @throws IllegalArgumentException this exception is thrown if in the database
     * already exist a Match object with the MatchDTO object's id
     */
    @Transactional
    @Override
    public MatchDTO create(MatchDTO dto) throws IllegalArgumentException {
        if(dto.getId() != null && matchRepository.existsById(dto.getId())) {
            throw new IllegalArgumentException("Match ID already exists!");
        }
        Match match = matchConverter.toEntity(dto);
        if(match == null) {
            return null;
        }
        Match created = matchRepository.save(match);
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
     * @throws RuntimeException this exception is thrown when the user try to update not
     * existing Match object or when the passed id not match the passed Match update DTO
     * object's id
     */
    @Override
    public void update(Long id, MatchDTO dto) throws RuntimeException {
        if(!matchRepository.existsById(id)) {
            throw new RuntimeException("Match not exists!");
        }
        if(!id.equals(dto.getId())) {
            throw new RuntimeException("Passed id not matching Match update DTO object's id!");
        }

        Match match = matchConverter.toEntity(dto);
        matchRepository.save(match);
    }

    /**
     * Deletes a definite Match object via its id from the database
     * @param id the definite Match object's id
     * @throws RuntimeException this exception is thrown when the user try to delete not
     * existing Match object
     */
    @Transactional
    @Override
    public void deleteById(Long id) throws RuntimeException {
        if(matchRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Match not exists!");
        }

        if(matchRepository.findById(id).isPresent()) {
            Match match = matchRepository.findById(id).get();
            supportingService.deleteById(Team.class, Match.class, match.getATeam().getId(), match.getId());
            supportingService.deleteById(Team.class, Match.class, match.getBTeam().getId(), match.getId());
            matchRepository.deleteById(id);
        }
    }
}