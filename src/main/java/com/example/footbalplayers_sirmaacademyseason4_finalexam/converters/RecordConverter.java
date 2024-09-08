package com.example.footbalplayers_sirmaacademyseason4_finalexam.converters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.converters.interfaces.Convertable;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.MatchDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.PlayerDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.RecordDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Match;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Player;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Record;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.MatchRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecordConverter implements Convertable<Record, RecordDTO> {
    private final MatchRepository matchRepository;
    private final MatchConverter matchConverter;
    private final PlayerRepository playerRepository;
    private final PlayerConverter playerConverter;

    /**
     * RecordConverter class constructor with arguments
     * @param matchRepository the match repository
     * @param matchConverter the match converter
     * @param playerRepository the player repository
     * @param playerConverter the player converter
     */
    @Autowired
    public RecordConverter(MatchRepository matchRepository,
                           MatchConverter matchConverter,
                           PlayerRepository playerRepository,
                           PlayerConverter playerConverter) {
        this.matchRepository = matchRepository;
        this.matchConverter = matchConverter;
        this.playerRepository = playerRepository;
        this.playerConverter = playerConverter;
    }

    /**
     * Converts a RecordDTO object to a Record object
     * @param recordDTO the RecordDTO object
     * @return a Record object
     */
    @Override
    public Record toEntity(RecordDTO recordDTO) {
        Record record = new Record();
        record.setId(recordDTO.getId());
        Match match = matchRepository.findById(recordDTO.getMatchId()).orElse(null);
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
        RecordDTO recordDTO = new RecordDTO();
        recordDTO.setId(record.getId());
        MatchDTO matchDTO = matchConverter.toDTO(record.getMatch());
        recordDTO.setMatchId(matchDTO.getId());
        PlayerDTO playerDTO = playerConverter.toDTO(record.getPlayer());
        recordDTO.setPlayerId(playerDTO.getId());
        recordDTO.setFromMinutes(record.getFromMinutes());
        recordDTO.setToMinutes(recordDTO.getToMinutes());

        return recordDTO;
    }
}