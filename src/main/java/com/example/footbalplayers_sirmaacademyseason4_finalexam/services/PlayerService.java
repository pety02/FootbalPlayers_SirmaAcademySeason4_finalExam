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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
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
    public PlayerService(PlayerAdapter playerConverter,
                         PlayerRepository playerRepository,
                         RecordRepository recordRepository,
                         SupportingTableService supportingTableService) {
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
     */
    @Override
    public PlayerDTO create(PlayerDTO dto) {
        PlayerDTO created = playerConverter.toDTO(playerRepository.save(playerConverter.toEntity(dto)));

        List<Record> records = recordRepository.findAll();
        SupportingTableDTO playerRecordDTO = new SupportingTableDTO();
        SupportingTableDTO teamPlayerDTO = new SupportingTableDTO();
        boolean areCreated = false;
        for(Record record : records) {
            for(Long currRecordId : dto.getRecordsIds()) {
                if(record.getId().equals(currRecordId)) {
                    record.getPlayer().setId(created.getId());
                    playerRecordDTO = new SupportingTableDTO(created.getId(), record.getId());
                    teamPlayerDTO = new SupportingTableDTO(created.getTeamId(), created.getId());
                    areCreated = true;
                    break;
                }
            }
        }
        if(areCreated) {
            supportingTableService.create(Player.class, Record.class, playerRecordDTO);
            supportingTableService.create(Team.class, Player.class, teamPlayerDTO);
            recordRepository.saveAll(records);
        }

        return created;
    }

    /**
     * Updates PlayerDTO object via its id in the database
     * @param id the Player object's id
     * @param dto updated fields of the Player object packaged in a PlayerDTO object
     */
    @Override
    public void update(Long id, PlayerDTO dto) {
        if(playerRepository.findById(id).isPresent() && id.equals(dto.getId())) {
            playerConverter.toDTO(playerRepository.save(playerConverter.toEntity(dto)));
        }
    }

    /**
     * Deletes a Player object from the database via its id and updates
     * the Records table and supporting tables in the database
     * @param id the Player object's id
     */
    @Override
    public void deleteById(Long id) {
        if(playerRepository.findById(id).isPresent()) {
            PlayerDTO playerDTO = playerConverter.toDTO(playerRepository.findById(id).get());
            List<Long> recordsIds = playerDTO.getRecordsIds();
            for (Long currRecordId : recordsIds) {
                supportingTableService.deleteById(Player.class, Record.class, playerDTO.getId(), currRecordId);
            }
            supportingTableService.deleteById(Team.class, Player.class, playerDTO.getTeamId(), playerDTO.getId());
            playerRepository.deleteById(id);
        }
    }
}