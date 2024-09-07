package com.example.footbalplayers_sirmaacademyseason4_finalexam.converters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.converters.interfaces.Convertable;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.RecordDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Record;
import org.springframework.stereotype.Component;

// TODO: to comment the class
@Component
public class RecordConverter implements Convertable<Record, RecordDTO> {
    /**
     *
     * @param recordDTO
     * @return
     */
    @Override
    public Record toEntity(RecordDTO recordDTO) {
        // TODO: to implement it
        return null;
    }

    /**
     *
     * @param record
     * @return
     */
    @Override
    public RecordDTO toDTO(Record record) {
        // TODO: to implement it
        return null;
    }
}