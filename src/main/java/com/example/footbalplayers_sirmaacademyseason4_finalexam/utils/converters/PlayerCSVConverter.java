package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.converters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.PlayerDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.interfaces.CSVConvertable;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class PlayerCSVConverter implements CSVConvertable<PlayerDTO> {

    /**
     * Converts a List of Map of String and String to List of PlayerDTO
     * @param data the List of Map of String and String
     * @return a List of PlayerDTO representation of all read lines from the CSV file
     */
    @Override
    public List<PlayerDTO> convertToListOfModel(List<Map<String, String>> data) {
        List<PlayerDTO> players = new ArrayList<>();
        for(Map<String, String> line : data) {
            String[] fields = line.values().toArray(new String[0]);
            try {
                Long id = Long.parseLong(fields[3]);
                Integer teamNumber = Integer.parseInt(fields[2]);
                String position = fields[0];
                String fullName = fields[1];
                Long teamId = Long.parseLong(fields[4]);
                PlayerDTO currentPlayerDTO = new PlayerDTO(id, teamNumber, position, fullName, teamId, new ArrayList<>());
                players.add(currentPlayerDTO);
            } catch (Exception ex) {
                log.error("Exception occurred: " + ex.getMessage());
                return players;
            }
        }
        return players;
    }

    /**
     * Gets a modified full name as adds spaces where it is needed after splitting fields by comma separator
     * from the Player CSV file in order receiving a valid full name (first name starts with a capital letter and
     * the surname also starts with a capital letter)
     * @param fields the split fields from the CSV file
     * @return a valid full name
     */
    private static StringBuilder getModifiedFullName(String[] fields) {
        String fullName = fields[1];
        StringBuilder modifiedFullName = new StringBuilder(fullName.charAt(0) + "");
        int i = 1;
        for(; i < fullName.length(); ++i) {
            char currentChar = fullName.charAt(i);
            if('A' <= currentChar && currentChar <= 'Z') {
                modifiedFullName.append(' ');
                modifiedFullName.append(currentChar);
                break;
            }

            modifiedFullName.append(currentChar);
        }
        if(i + 1 < fullName.length()) {
            modifiedFullName.append(fullName.substring(i + 1));
        }
        return modifiedFullName;
    }
}