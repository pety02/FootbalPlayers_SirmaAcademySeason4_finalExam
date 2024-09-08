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
     *
     * @param data
     * @return
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
     *
     * @param objs
     * @return
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