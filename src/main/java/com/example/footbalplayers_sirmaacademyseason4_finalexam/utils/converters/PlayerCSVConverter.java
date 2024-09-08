package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.converters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.PlayerDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.interfaces.CSVConvertable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerCSVConverter implements CSVConvertable<PlayerDTO> {

    /**
     *
     * @param data
     * @return
     */
    @Override
    public List<PlayerDTO> convertToListOfModel(List<Map<String, String>> data) {
        List<PlayerDTO> players = new ArrayList<>();
        for(Map<String, String> line : data) {
            String[] fields = line.values().toArray(new String[0]);
            try {
                Long id = Long.parseLong(fields[0]);
                Integer teamNumber = Integer.parseInt(fields[1]);
                String position = fields[2];
                String fullName = fields[3];
                Long teamId = Long.parseLong(fields[4]);
                PlayerDTO currentPlayerDTO = new PlayerDTO(id, teamNumber, position, fullName, teamId, new ArrayList<>());
                players.add(currentPlayerDTO);
            } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                continue;
            } catch (Exception ex) {
                return players;
            }
        }
        return players;
    }

    /**
     *
     * @param objs
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public List<Map<String, String>> convertToCSV(List<PlayerDTO> objs) throws IllegalArgumentException{
        List<Map<String, String>> data = new ArrayList<>();
        String[] headers = objs.getFirst().toString().split(",");
        if(headers.length == 0) {
            throw new IllegalArgumentException("The CSV file is in incorrect format or empty!");
        }

        for (PlayerDTO player : objs) {
            Map<String, String> obj = new HashMap<>();

            String[] fields = new String[] {
                    player.getId() + "",
                    player.getTeamNumber() + "",
                    player.getPosition(),
                    player.getFullName(),
                    player.getTeamId() + ""
            };

            for (int i = 0; i < headers.length; i++) {
                obj.put(headers[i], fields[i]);
            }
            data.add(obj);
        }

        return data;
    }
}