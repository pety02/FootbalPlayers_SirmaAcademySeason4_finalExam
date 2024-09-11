package com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters.interfaces.Adaptable;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.RecordDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Match;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Player;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Record;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.MatchRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.PlayerRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class RecordAdapter implements Adaptable<Record, RecordDTO> {
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;

    /**
     * RecordConverter class constructor with arguments
     * @param matchRepository the match repository
     * @param playerRepository the player repository
     */
    @Autowired
    public RecordAdapter(@NonNull MatchRepository matchRepository,
                         @NonNull PlayerRepository playerRepository) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
    }

    /**
     * Converts a RecordDTO object to a Record object
     * @param recordDTO the RecordDTO object
     * @return a Record object
     * @throws IllegalArgumentException this exception is thrown
     * if the Record object's match id is null or there is no such
     * match in the database
     */
    @Override
    public Record toEntity(RecordDTO recordDTO) throws IllegalArgumentException {
        if(recordDTO == null) {
            return null;
        }
        Record record = new Record();
        record.setId(recordDTO.getId());
        if(recordDTO.getMatchId() == null) {
            throw new IllegalArgumentException("Record's match id could not be null!");
        }
        Match match = matchRepository.findById(recordDTO.getMatchId()).orElse(null);
        if(match == null) {
            throw new IllegalArgumentException("Record's match could not be null!");
        }
        record.setMatch(match);
        Player player = playerRepository.findById(recordDTO.getPlayerId()).orElse(null);
        record.setPlayer(player);
        record.setFromMinutes(recordDTO.getFromMinutes());
        record.setToMinutes(recordDTO.getToMinutes());


        return record;
    }

    /**
     * Converts a Record object to a RecordDTO object
     * @param record the Record object
     * @return a RecordDTO object
     */
    @Override
    public RecordDTO toDTO(Record record) {
        if(record == null) {
            return null;
        }
        RecordDTO recordDTO = new RecordDTO();
        recordDTO.setId(record.getId());
        recordDTO.setMatchId(record.getMatch().getId());
        recordDTO.setPlayerId(record.getPlayer().getId());
        recordDTO.setFromMinutes(record.getFromMinutes());
        recordDTO.setToMinutes(record.getToMinutes());

        return recordDTO;
    }
}