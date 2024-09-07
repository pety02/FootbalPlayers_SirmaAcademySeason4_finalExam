package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.RecordDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.MatchRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.PlayerRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.RecordRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// TODO: to comment the class
@org.springframework.stereotype.Service
public class RecordService implements Service<Record, RecordDTO> {
    private final RecordRepository recordRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;

    /**
     *
     * @param recordRepository
     * @param playerRepository
     * @param matchRepository
     */
    @Autowired
    public RecordService(RecordRepository recordRepository,
                         PlayerRepository playerRepository,
                         MatchRepository matchRepository) {
        this.recordRepository = recordRepository;
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public RecordDTO loadByID(Long id) {
        // TODO: to implement it
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<RecordDTO> loadAll() {
        // TODO: to implement it
        return null;
    }

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public RecordDTO create(RecordDTO entity) {
        // TODO: to implement it
        return null;
    }

    /**
     *
     * @param id
     * @param entity
     */
    @Override
    public void update(Long id, RecordDTO entity) {
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