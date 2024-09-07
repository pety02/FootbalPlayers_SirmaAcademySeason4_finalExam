package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.MatchDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Match;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.MatchRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.TeamRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// TODO: to comment the class
@org.springframework.stereotype.Service
public class MatchService implements Service<Match, MatchDTO> {
    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    /**
     *
     * @param teamRepository
     * @param matchRepository
     */
    @Autowired
    public MatchService(TeamRepository teamRepository,
                        MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public MatchDTO loadByID(Long id) {
        // TODO: to implement it
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<MatchDTO> loadAll() {
        // TODO: to implement it
        return null;
    }

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public MatchDTO create(MatchDTO entity) {
        // TODO: to implement it
        return null;
    }

    /**
     *
     * @param id
     * @param entity
     */
    @Override
    public void update(Long id, MatchDTO entity) {
        // TODO: to implement it
    }

    /**
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        // TODO: to implement it
    }
}