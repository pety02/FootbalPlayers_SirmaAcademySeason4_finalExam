package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters.TeamAdapter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.TeamDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.TeamRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces.Service;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
@Validated
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
    public TeamService(@NonNull TeamAdapter teamConverter,
                       @NonNull TeamRepository teamRepository) {
        this.teamConverter = teamConverter;
        this.teamRepository = teamRepository;
    }

    /**
     * Loads a Team object from the database by its id
     * and converts it to TeamDTO object
     *
     * @param id the Team object id
     * @return a TeamDTO object
     * @throws RuntimeException this exception is thrown if the
     * TeamAdapter object cannot convert properly Team object to
     * TeamDTO object
     */
    @Transactional
    @Override
    public TeamDTO loadByID(Long id) throws RuntimeException {
        Team team = teamRepository.findById(id).orElse(null);
        TeamDTO teamDTO = teamConverter.toDTO(team);
        if(teamDTO == null) {
            throw new RuntimeException("Cannot convert Team object to TeamDTO object!");
        }

        return teamDTO;
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
     * @throws IllegalArgumentException this exception is thrown when in the database
     * exists a team with this id
     */
    @Override
    public TeamDTO create(TeamDTO dto) throws IllegalArgumentException {
        if(dto.getId() != null && teamRepository.existsById(dto.getId())) {
            throw new IllegalArgumentException("Team ID already exists!");
        }

        Team team = teamConverter.toEntity(dto);
        return teamConverter.toDTO(teamRepository.save(team));
    }

    /**
     * Updates a Team object by its id and TeamDTO object
     * that packages the updated fields
     *
     * @param id  the id of the Team object that should be updated
     * @param dto the TeamDTO object with updated fields
     * @throws RuntimeException this exception is thrown when a team with this
     * id do not exist in the database or the passes id and the TeamDTO object's
     * id do not match
     */
    @Override
    public void update(Long id, TeamDTO dto) throws RuntimeException {
        if(!teamRepository.existsById(id)) {
            throw new RuntimeException("Team not exists!");
        }
        if(!id.equals(dto.getId())) {
            throw new RuntimeException("Passed id not matching Team update DTO object's id!");
        }

        Team team = teamConverter.toEntity(dto);
        teamRepository.save(team);
    }

    /**
     * Deletes a Team object by its id
     *
     * @param id the id of the Team object that should be deleted
     * @throws RuntimeException this exception is thrown when a team
     * with this id do not exist in the database
     */
    @Override
    public void deleteById(Long id) throws RuntimeException {
        if(teamRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Team not exists!");
        }

        teamRepository.deleteById(id);
    }
}