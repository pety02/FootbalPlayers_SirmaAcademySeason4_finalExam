package com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters.interfaces.Adaptable;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.PlayerDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Player;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Record;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.RecordRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerAdapter implements Adaptable<Player, PlayerDTO> {
    private final TeamRepository teamRepository;
    private final RecordRepository recordRepository;

    /**
     * PlayerConverter constructor with arguments
     * @param teamRepository the team repository
     * @param recordRepository the record repository
     */
    @Autowired
    public PlayerAdapter(TeamRepository teamRepository,
                         RecordRepository recordRepository) {
        this.teamRepository = teamRepository;
        this.recordRepository = recordRepository;
    }

    /**
     * Converts a PlayerDTO object to a Player object
     * @param playerDTO the PlayerDTO object
     * @return a Player object
     */
    @Override
    public Player toEntity(PlayerDTO playerDTO) {
        if(playerDTO == null) {
            return null;
        }
        Player player = new Player();
        player.setId(playerDTO.getId());
        player.setTeamNumber(playerDTO.getTeamNumber());
        player.setPosition(playerDTO.getPosition());
        player.setFullName(playerDTO.getFullName());
        Team team = teamRepository.findById(playerDTO.getTeamId()).orElse(null);
        if(team == null) {
            throw new IllegalArgumentException("Every player should play at a team!");
        }
        player.setTeam(team);
        List<Long> recordsIds = playerDTO.getRecordsIds();
        List<Record> records = new ArrayList<>();
        for(Long recordId : recordsIds) {
            records.add(recordRepository.findById(recordId).orElse(null));
        }
        player.setRecords(records);
        return player;
    }

    /**
     * Converts a Player object to a PlayerDTO object
     * @param player the Player object
     * @return a PlayerDTO object
     */
    @Override
    public PlayerDTO toDTO(Player player) {
        if(player == null) {
            return null;
        }
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setTeamNumber(player.getTeamNumber());
        playerDTO.setPosition(player.getPosition());
        playerDTO.setFullName(player.getFullName());
        playerDTO.setTeamId(player.getTeam().getId());
        List<Record> records = player.getRecords();
        List<Long> recordsIds = new ArrayList<>();
        if(records != null) {
            for (Record record : records) {
                recordsIds.add(record.getId());
            }
        }
        playerDTO.setRecordsIds(recordsIds);
        return playerDTO;
    }
}