package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters.RecordAdapter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.RecordDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.SupportingTableDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Player;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Record;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.RecordRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces.Service;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
@Validated
public class RecordService implements Service<Record, RecordDTO> {
    private final RecordRepository recordRepository;
    private final RecordAdapter recordConverter;
    private final SupportingTableService supportingTableService;
    /**
     * RecordService class constructor with arguments
     *
     * @param recordRepository          the record repository
     * @param recordConverter           the record converter
     * @param supportingTableService    the supporting tables service
     */
    @Autowired
    public RecordService(RecordRepository recordRepository,
                         RecordAdapter recordConverter,
                         SupportingTableService supportingTableService) {
        this.recordRepository = recordRepository;
        this.recordConverter = recordConverter;
        this.supportingTableService = supportingTableService;
    }

    /**
     * Loads a Record object by its id from the database, converts it
     * to a RecordDTO object and return the convertd object
     * @param id the record's id
     * @return the converted object
     */
    @Override
    public RecordDTO loadByID(Long id) {
        Record record = recordRepository.findById(id).orElse(null);
        return recordConverter.toDTO(record);
    }

    /**
     * Loads all Record objects from the database and converts them
     * to RecordDTO objects and return them as a List of RecordDTO objects
     * @return a List of RecordDTO objects
     */
    @Override
    public List<RecordDTO> loadAll() {
        List<Record> records = recordRepository.findAll();
        List<RecordDTO> recordDTOs = new ArrayList<>();
        for(Record record : records) {
            recordDTOs.add(recordConverter.toDTO(record));
        }

        return recordDTOs;
    }

    /**
     * Creates a Record object from the RecordDTO object in the database
     * @param dto the RecordDTO object
     * @return the created object, converted to a RecordDTO object
     * @throws IllegalArgumentException this exception is thrown when in the database
     * exists a record with this id
     */
    @Transactional
    @Override
    public RecordDTO create(RecordDTO dto) throws IllegalArgumentException {
        if(dto.getId() != null && recordRepository.existsById(dto.getId())) {
            throw new IllegalArgumentException("Record ID already exists!");
        }

        Record record = recordConverter.toEntity(dto);
        RecordDTO savedRecordDTO = recordConverter.toDTO(recordRepository.save(record));
        SupportingTableDTO playerRecord = new SupportingTableDTO(savedRecordDTO.getPlayerId(), savedRecordDTO.getId());
        supportingTableService.create(Player.class, Record.class, playerRecord);
        return savedRecordDTO;
    }

    /**
     * Updates a definite Record object by its id in the database
     * @param id the Record object's id
     * @param dto the updated fields packaged as a RecordDTO object
     * @throws RuntimeException this exception is thrown when a record with this
     * id do not exist in the database or the passes id and the RecordDTO object's
     * id do not match
     */
    @Override
    public void update(Long id, RecordDTO dto) throws RuntimeException {
        if(!recordRepository.existsById(id)) {
            throw new RuntimeException("Record not exists!");
        }
        if(!id.equals(dto.getId())) {
            throw new RuntimeException("Passed id not matching Record update DTO object's id!");
        }

        Record record = recordConverter.toEntity(dto);
        recordRepository.save(record);
    }

    /**
     * Deletes a Record object by its id from the database
     * @param id the Record object's id
     * @throws RuntimeException this exception is thrown when a record
     * with this id do not exist in the database
     */
    @Override
    public void deleteById(Long id) throws RuntimeException {
        if(recordRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Record not exists!");
        }

        recordRepository.deleteById(id);
    }
}