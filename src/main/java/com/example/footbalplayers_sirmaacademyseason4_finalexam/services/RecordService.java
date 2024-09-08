package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters.RecordAdapter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.RecordDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Record;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.RecordRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class RecordService implements Service<Record, RecordDTO> {
    private final RecordRepository recordRepository;
    private final RecordAdapter recordConverter;

    /**
     * RecordService class constructor with arguments
     * @param recordRepository the record repository
     * @param recordConverter the record converter
     */
    @Autowired
    public RecordService(RecordRepository recordRepository,
                         RecordAdapter recordConverter) {
        this.recordRepository = recordRepository;
        this.recordConverter = recordConverter;
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
     */
    @Override
    public RecordDTO create(RecordDTO dto) {
        Record record = recordConverter.toEntity(dto);
        return recordConverter.toDTO(recordRepository.save(record));
    }

    /**
     * Updates a definite Record object by its id in the database
     * @param id the Record object's id
     * @param dto the updated fields packaged as a RecordDTO object
     */
    @Override
    public void update(Long id, RecordDTO dto) {
        if(recordRepository.existsById(id) && dto.getId().equals(id)) {
            Record record = recordConverter.toEntity(dto);
            recordRepository.save(record);
        }
    }

    /**
     * Deletes a Record object by its id from the database
     * @param id the Record object's id
     */
    @Override
    public void deleteById(Long id) {
        recordRepository.deleteById(id);
    }
}