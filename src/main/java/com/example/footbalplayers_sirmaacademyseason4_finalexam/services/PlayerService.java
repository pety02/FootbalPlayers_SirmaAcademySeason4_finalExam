package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters.PlayerAdapter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.PlayerDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.SupportingTableDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Player;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Record;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.PlayerRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.RecordRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces.Service;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
@Validated
public class PlayerService implements Service<Player, PlayerDTO> {
    private final PlayerAdapter playerConverter;
    private final PlayerRepository playerRepository;
    private final RecordRepository recordRepository;
    private final SupportingTableService supportingTableService;

    /**
     * PlayerService constructor with parameters
     * @param playerConverter the player converter
     * @param playerRepository the player repository
     * @param recordRepository the record repository
     * @param supportingTableService the supporting tables service
     */
    @Autowired
    public PlayerService(@NonNull PlayerAdapter playerConverter,
                         @NonNull PlayerRepository playerRepository,
                         @NonNull RecordRepository recordRepository,
                         @NonNull SupportingTableService supportingTableService) {
        this.playerConverter = playerConverter;
        this.playerRepository = playerRepository;
        this.recordRepository = recordRepository;
        this.supportingTableService = supportingTableService;
    }

    /**
     * Loads a Player object via its id from the database, converts
     * it to PlayerDTO object and returns the converted object
     * @param id the Player object id
     * @return the converted object
     */
    @Override
    public PlayerDTO loadByID(Long id) {
        return playerConverter.toDTO(playerRepository
                .findById(id)
                .orElse(null));
    }

    /**
     * Loads all Player objects from the database, converts all of them to
     * PlayerDTO object and returns a List of PlayerDTO objects
     * @return a List of PlayerDTO objects
     */
    @Override
    public List<PlayerDTO> loadAll() {
        List<Player> players = playerRepository.findAll();
        List<PlayerDTO> playersDTOs = new ArrayList<>();
        for (Player player : players) {
            PlayerDTO playerDTO = playerConverter.toDTO(player);
            playersDTOs.add(playerDTO);
        }
        return playersDTOs;
    }

    /**
     * Creates Player object from the PlayerDTO object and store it in the database.
     * Also, stores updated records in the database and creates and stores playerRecord
     * and teamPlayer records.
     * @param dto the PlayerDTO object
     * @return converted to PlayerDTO object
     * @throws IllegalArgumentException this exception is thrown when the user pass invalid player id
     * or invalid player team number
     */
    @Transactional
    @Override
    public PlayerDTO create(PlayerDTO dto) throws IllegalArgumentException {
        if(dto.getId() != null && playerRepository.existsById(dto.getId())) {
            throw new IllegalArgumentException("Player ID already exists!");
        }

        PlayerDTO created = playerConverter.toDTO(playerRepository.save(playerConverter.toEntity(dto)));

        SupportingTableDTO teamPlayerDTO = new SupportingTableDTO(created.getTeamId(), created.getId());
        supportingTableService.create(Team.class, Player.class, teamPlayerDTO);

        return created;
    }

    /**
     * Updates PlayerDTO object via its id in the database
     * @param id the Player object's id
     * @param dto updated fields of the Player object packaged in a PlayerDTO object
     * @throws RuntimeException this exception is thrown when the user try to update not
     * existing Player object or when the passed id not match the passed Player update DTO
     * object's id
     */
    @Override
    public void update(Long id, PlayerDTO dto) throws RuntimeException {
        if(!playerRepository.existsById(id)) {
            throw new RuntimeException("Player not exists!");
        }
        if(!id.equals(dto.getId())) {
            throw new RuntimeException("Passed id not matching Player update DTO object's id!");
        }

        playerConverter.toDTO(playerRepository.save(playerConverter.toEntity(dto)));
    }

    /**
     * Deletes a Player object from the database via its id and updates
     * the Records table and supporting tables in the database
     * @param id the Player object's id
     * @throws RuntimeException this exception is thrown when the user try to delete not
     * existing Player object
     */
    @Transactional
    @Override
    public void deleteById(Long id) throws RuntimeException {
        if(playerRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Player not exists!");
        }

        PlayerDTO playerDTO = playerConverter.toDTO(playerRepository.findById(id).get());
        List<Long> recordsIds = playerDTO.getRecordsIds();
        for (Long currRecordId : recordsIds) {
            supportingTableService.deleteById(Player.class, Record.class, playerDTO.getId(), currRecordId);
        }
        supportingTableService.deleteById(Team.class, Player.class, playerDTO.getTeamId(), playerDTO.getId());
        playerRepository.deleteById(id);
    }
}