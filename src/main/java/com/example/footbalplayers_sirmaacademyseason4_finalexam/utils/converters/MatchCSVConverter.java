package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.converters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.MatchDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.interfaces.CSVConvertable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchCSVConverter implements CSVConvertable<MatchDTO> {

    /**
     * Converts a List of Map of String and String to List of MatchDTO
     * @param data the List of Map of String and String
     * @return a List of MatchDTO representation of all read lines from the CSV file
     */
    @Override
    public List<MatchDTO> convertToListOfModel(List<Map<String, String>> data) {
        List<MatchDTO> matchDTOs = new ArrayList<>();
        for(Map<String, String> line : data) {
            String[] fields = line.values().toArray(new String[0]);
            try {
                Long id = Long.parseLong(fields[0]);
                Long aTeamId = Long.parseLong(fields[1]);
                Long bTeamId = Long.parseLong(fields[2]);
                LocalDate date = LocalDate.parse(fields[3]);
                String score = fields[4];
                MatchDTO currentPlayerDTO = new MatchDTO(id, aTeamId, bTeamId, date, score);
                matchDTOs.add(currentPlayerDTO);
            } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                continue;
            } catch (Exception ex) {
                return matchDTOs;
            }
        }
        return matchDTOs;
    }

    /**
     * Converts a List of MatchDTO to List of Map of String and String
     * @param objs the List of MatchDTO
     * @return a List of Map of String and String representation of all lines of CSV file
     */
    @Override
    public List<Map<String, String>> convertToCSV(List<MatchDTO> objs) {
        List<Map<String, String>> data = new ArrayList<>();
        String[] headers = objs.getFirst().toString().split(",");
        if(headers.length == 0) {
            throw new IllegalArgumentException("The CSV file is in incorrect format or empty!");
        }

        for (MatchDTO match : objs) {
            Map<String, String> obj = new HashMap<>();

            String[] fields = new String[] {
                    match.getId() + "",
                    match.getATeamId() + "",
                    match.getBTeamId() + "",
                    match.getDate().toString(),
                    match.getScore()
            };

            for (int i = 0; i < headers.length; i++) {
                obj.put(headers[i], fields[i]);
            }
            data.add(obj);
        }

        return data;
    }
}