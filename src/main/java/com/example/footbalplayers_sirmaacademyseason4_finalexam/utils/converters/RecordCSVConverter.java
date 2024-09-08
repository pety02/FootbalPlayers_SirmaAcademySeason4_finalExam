package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.converters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.RecordDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.interfaces.CSVConvertable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                Long id = Long.parseLong(fields[0]);
                Long playerId = Long.parseLong(fields[1]);
                Long matchId = Long.parseLong(fields[2]);
                Integer fromMinutes = Integer.parseInt(fields[3]);
                Integer toMinutes = Integer.parseInt(fields[4]);
                RecordDTO currentPlayerDTO = new RecordDTO(id, playerId, matchId, fromMinutes, toMinutes);
                recordDTOs.add(currentPlayerDTO);
            } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                continue;
            } catch (Exception ex) {
                return recordDTOs;
            }
        }
        return recordDTOs;
    }

    /**
     * Converts a List of RecordDTO to List of Map of String and String
     * @param objs the List of RecordDTO
     * @return a List of Map of String and String representation of all lines of CSV file
     */
    @Override
    public List<Map<String, String>> convertToCSV(List<RecordDTO> objs) {
        List<Map<String, String>> data = new ArrayList<>();
        String[] headers = objs.getFirst().toString().split(",");
        if(headers.length == 0) {
            throw new IllegalArgumentException("The CSV file is in incorrect format or empty!");
        }

        for (RecordDTO record : objs) {
            Map<String, String> obj = new HashMap<>();

            String[] fields = new String[] {
                    record.getId() + "",
                    record.getPlayerId() + "",
                    record.getMatchId() + "",
                    record.getFromMinutes() + "",
                    record.getToMinutes() + ""
            };

            for (int i = 0; i < headers.length; i++) {
                obj.put(headers[i], fields[i]);
            }
            data.add(obj);
        }

        return data;
    }
}