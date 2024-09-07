package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.converters.TeamConverter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.TeamDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.TeamRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

// TODO: to comment the class
@org.springframework.stereotype.Service
public class TeamService implements Service<Team, TeamDTO> {
    private final TeamConverter teamConverter;
    private final TeamRepository teamRepository;

    /**
     *
     * @param teamConverter
     * @param teamRepository
     */
    @Autowired
    public TeamService(TeamConverter teamConverter,
                       TeamRepository teamRepository) {
        this.teamConverter = teamConverter;
        this.teamRepository = teamRepository;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public TeamDTO loadByID(Long id) {
        return teamConverter.toDTO(teamRepository.findById(id).orElse(null));
    }

    /**
     *
     * @return
     */
    @Override
    public List<TeamDTO> loadAll() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDTO> teamDTOs = new ArrayList<>();
        for(Team team : teams) {
            teamDTOs.add(teamConverter.toDTO(team));
        }

        return teamDTOs;
    }

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public TeamDTO create(TeamDTO entity) {
        // TODO: to implement it
        return null;
    }

    /**
     *
     * @param id
     * @param entity
     */
    @Override
    public void update(Long id, TeamDTO entity) {
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