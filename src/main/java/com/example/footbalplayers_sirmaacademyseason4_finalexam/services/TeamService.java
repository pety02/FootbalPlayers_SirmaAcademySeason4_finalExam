package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters.TeamAdapter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.TeamDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.TeamRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class TeamService implements Service<Team, TeamDTO> {
    private final TeamAdapter teamConverter;
    private final TeamRepository teamRepository;

    /**
     * TeamService class constructor with arguments
     *
     * @param teamConverter  the team converter
     * @param teamRepository the team repository
     */
    @Autowired
    public TeamService(TeamAdapter teamConverter,
                       TeamRepository teamRepository) {
        this.teamConverter = teamConverter;
        this.teamRepository = teamRepository;
    }

    /**
     * Loads a Team object from the database by its id
     * and converts it to TeamDTO object
     *
     * @param id the Team object id
     * @return a TeamDTO object
     */
    @Override
    public TeamDTO loadByID(Long id) {
        return teamConverter.toDTO(teamRepository.findById(id).orElse(null));
    }

    /**
     * Loads all Team object from the database and converts them
     * to TeamDTO objects
     *
     * @return a List of TeamDTO objects
     */
    @Override
    public List<TeamDTO> loadAll() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDTO> teamDTOs = new ArrayList<>();
        for (Team team : teams) {
            teamDTOs.add(teamConverter.toDTO(team));
        }

        return teamDTOs;
    }

    /**
     * Creates a Team object from a TeamDTO object
     * and saves it in the database
     *
     * @param dto the TeamDTO object
     * @return the saved object converted to TeamDTO object
     */
    @Override
    public TeamDTO create(TeamDTO dto) {
        Team team = teamConverter.toEntity(dto);
        return teamConverter.toDTO(teamRepository.save(team));
    }

    /**
     * Updates a Team object by its id and TeamDTO object
     * that packages the updated fields
     *
     * @param id  the id of the Team object that should be updated
     * @param dto the TeamDTO object with updated fields
     */
    @Override
    public void update(Long id, TeamDTO dto) {
        if (teamRepository.existsById(id) && dto.getId().equals(id)) {
            Team team = teamConverter.toEntity(dto);
            teamRepository.save(team);
        }
    }

    /**
     * Deletes a Team object by its id
     *
     * @param id the id of the Team object that should be deleted
     */
    @Override
    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }
}