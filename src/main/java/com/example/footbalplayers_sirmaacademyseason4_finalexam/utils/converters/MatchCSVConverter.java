package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.converters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.MatchDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.interfaces.CSVConvertable;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
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
                Long id = Long.parseLong(fields[3]);
                Long aTeamId = Long.parseLong(fields[0]);
                Long bTeamId = Long.parseLong(fields[2]);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
                LocalDate date = LocalDate.parse(fields[4], formatter);
                String score = fields[1];

                MatchDTO currentMatchDTO = new MatchDTO(id, aTeamId, bTeamId, date, score);
                matchDTOs.add(currentMatchDTO);
            } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                log.error("Error occurred: " + ex.getMessage());
            } catch (Exception ex) {
                log.error("Error occurred: " + ex.getMessage());
                return matchDTOs;
            }
        }

        return matchDTOs;
    }
}