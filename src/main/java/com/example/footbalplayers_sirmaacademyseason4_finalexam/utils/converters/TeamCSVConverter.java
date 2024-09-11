package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.converters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.TeamDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.interfaces.CSVConvertable;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
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
                Long id = Long.parseLong(fields[1]);
                String name = fields[3];
                String managerFullName = getModifiedName(name).toString();
                String group = fields[0];
                TeamDTO teamDTO = new TeamDTO(id, name, managerFullName, group, new ArrayList<>(), new ArrayList<>());
                teamDTOs.add(teamDTO);
            } catch (Exception ex) {
                log.error("Exception occurred: " + ex.getMessage());
                return teamDTOs;
            }
        }
        return teamDTOs;
    }

    /**
     * Gets a modified full name of the team's manager as adds spaces where it is needed after splitting fields
     * by comma separator from the Team CSV file in order receiving a valid full name (first name starts with a
     * capital letter and the surname also starts with a capital letter)
     * @param name the beginning version of name
     * @return a valid full name of the team's manager
     */
    private static StringBuilder getModifiedName(String name) {
        StringBuilder modifiedName = new StringBuilder(name.charAt(0) + "");
        int i = 0;
        for(; i < name.length(); ++i) {
            char currentChar = name.charAt(i);
            if('A' <= currentChar && currentChar <= 'Z') {
                modifiedName.append(' ');
            }
            modifiedName.append(currentChar);
        }
        return modifiedName;
    }
}