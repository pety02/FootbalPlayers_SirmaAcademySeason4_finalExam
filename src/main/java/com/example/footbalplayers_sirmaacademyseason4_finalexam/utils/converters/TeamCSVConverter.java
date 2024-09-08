package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.converters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.TeamDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.interfaces.CSVConvertable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamCSVConverter implements CSVConvertable<TeamDTO> {

    /**
     * Converts a List of Map of String and String to List of TeamDTO
     * @param data the List of Map of String and String
     * @return a List of TeamDTO representation of all read lines from the CSV file
     */
    @Override
    public List<TeamDTO> convertToListOfModel(List<Map<String, String>> data) {
        List<TeamDTO> teamDTOs = new ArrayList<>();
        for(Map<String, String> line : data) {
            String[] fields = line.values().toArray(new String[0]);
            try {
                Long id = Long.parseLong(fields[0]);
                String name = fields[1];
                String managerFullName = fields[2];
                String group = fields[3];
                TeamDTO teamDTO = new TeamDTO(id, name, managerFullName, group, new ArrayList<>(), new ArrayList<>());
                teamDTOs.add(teamDTO);
            } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                continue;
            } catch (Exception ex) {
                return teamDTOs;
            }
        }
        return teamDTOs;
    }

    /**
     * Converts a List of TeamDTO to List of Map of String and String
     * @param objs the List of TeamDTO
     * @return a List of Map of String and String representation of all lines of CSV file
     */
    @Override
    public List<Map<String, String>> convertToCSV(List<TeamDTO> objs) {
        List<Map<String, String>> data = new ArrayList<>();
        String[] headers = objs.getFirst().toString().split(",");
        if(headers.length == 0) {
            throw new IllegalArgumentException("The CSV file is in incorrect format or empty!");
        }

        for (TeamDTO teamDTO : objs) {
            Map<String, String> obj = new HashMap<>();

            String[] fields = new String[] {
                    teamDTO.getId() + "",
                    teamDTO.getName(),
                    teamDTO.getManagerFullName(),
                    teamDTO.getGroup()
            };

            for (int i = 0; i < headers.length; i++) {
                obj.put(headers[i], fields[i]);
            }
            data.add(obj);
        }

        return data;
    }
}