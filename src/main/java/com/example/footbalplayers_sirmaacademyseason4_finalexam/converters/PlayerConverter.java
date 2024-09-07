package com.example.footbalplayers_sirmaacademyseason4_finalexam.converters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.converters.interfaces.Convertable;
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

// TODO: to comment the class
@Component
public class PlayerConverter implements Convertable<Player, PlayerDTO> {
    private final TeamRepository teamRepository;
    private final RecordRepository recordRepository;

    /**
     *
     * @param teamRepository
     */
    @Autowired
    public PlayerConverter(TeamRepository teamRepository,
                           RecordRepository recordRepository) {
        this.teamRepository = teamRepository;
        this.recordRepository = recordRepository;
    }

    /**
     *
     * @param playerDTO
     * @return
     */
    @Override
    public Player toEntity(PlayerDTO playerDTO) {
        if(playerDTO == null) {
            return null;
        }
        Player player = new Player();
        player.setId(player.getId());
        player.setTeamNumber(playerDTO.getTeamNumber());
        player.setPosition(player.getPosition());
        player.setFullName(player.getFullName());
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
     *
     * @param player
     * @return
     */
    @Override
    public PlayerDTO toDTO(Player player) {
        if(player == null) {
            return null;
        }
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setTeamNumber(playerDTO.getTeamNumber());
        playerDTO.setPosition(player.getPosition());
        playerDTO.setFullName(player.getFullName());
        playerDTO.setTeamId(playerDTO.getTeamId());
        List<Record> records = player.getRecords();
        List<Long> recordsIds = new ArrayList<>();
        for(Record record : records) {
            recordsIds.add(record.getId());
        }
        playerDTO.setRecordsIds(recordsIds);
        return playerDTO;
    }
}