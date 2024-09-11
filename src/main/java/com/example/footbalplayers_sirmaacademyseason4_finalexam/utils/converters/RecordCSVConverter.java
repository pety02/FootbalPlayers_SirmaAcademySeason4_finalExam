package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.converters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.RecordDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.interfaces.CSVConvertable;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class RecordCSVConverter implements CSVConvertable<RecordDTO> {

    /**
     * Converts a List of Map of String and String to List of RecordDTO
     * @param data the List of Map of String and String
     * @return a List of RecordDTO representation of all read lines from the CSV file
     */
    @Override
    public List<RecordDTO> convertToListOfModel(List<Map<String, String>> data) {
        List<RecordDTO> recordDTOs = new ArrayList<>();
        for(Map<String, String> line : data) {
            String[] fields = line.values().toArray(new String[0]);
            try {
                Long id = Long.parseLong(fields[4]);
                Long playerId = Long.parseLong(fields[0]);
                Long matchId = Long.parseLong(fields[3]);
                Integer fromMinutes = Integer.parseInt(fields[2]);
                int toMinutes;
                if(fields[1].equals("NULL")) {
                    toMinutes = 90;
                } else {
                    toMinutes = Integer.parseInt(fields[1]);
                }

                RecordDTO currentPlayerDTO = new RecordDTO(id, playerId, matchId, fromMinutes, toMinutes);
                recordDTOs.add(currentPlayerDTO);
            } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                log.error("Exception occurred:" + ex.getMessage());
            } catch (Exception ex) {
                log.error("Exception occurred:" + ex.getMessage());
                return recordDTOs;
            }
        }
        return recordDTOs;
    }
}