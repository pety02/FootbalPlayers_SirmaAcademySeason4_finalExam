package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.converters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.TeamDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.interfaces.CSVConvertable;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
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
                StringBuilder modifiedName = new StringBuilder(name.charAt(0) + "");
                int i = 1;
                for(; i < name.length(); ++i) {
                    char currentChar = name.charAt(i);
                    if('A' <= currentChar && currentChar <= 'Z') {
                        modifiedName.append(' ');
                        modifiedName.append(currentChar);
                        break;
                    }
                    modifiedName.append(currentChar);
                }
                if(i + 1 < name.length()) {
                    modifiedName.append(name.substring(i + 1));
                }
                String managerFullName = fields[2];
                StringBuilder managerFullNameModified = new StringBuilder(managerFullName.charAt(0) + "");
                for(int j = 1; j < managerFullName.length(); ++j) {
                    char currChar = managerFullName.charAt(j);
                    if('A' <= currChar && currChar <= 'Z') {
                        managerFullNameModified.append(' ');
                    }
                    managerFullNameModified.append(currChar);
                }
                managerFullName = managerFullNameModified.toString();
                name = modifiedName.toString();
                String group = fields[0];
                TeamDTO teamDTO = new TeamDTO(id, name, managerFullName, group, new ArrayList<>(), new ArrayList<>());
                System.out.println(teamDTO);
                teamDTOs.add(teamDTO);
            } catch (Exception ex) {
                log.error("Exception occurred: " + ex.getMessage());
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