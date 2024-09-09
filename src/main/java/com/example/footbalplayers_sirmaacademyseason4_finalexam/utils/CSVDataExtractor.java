package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.MatchDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.PlayerDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.RecordDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.TeamDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.converters.MatchCSVConverter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.converters.PlayerCSVConverter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.converters.RecordCSVConverter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.converters.TeamCSVConverter;
import java.util.List;

public class CSVDataExtractor {
    private final Reader reader;

    /**
     * CSVDataExtractor class constructor with parameters
     */
    public CSVDataExtractor() {
        this.reader = new Reader();
    }

    /**
     * Extracts all MatchDTO objects from a CSV file and returns them as a list
     * @param filename the CSV file's name
     * @return a List of MatchDTO objects
     * @throws RuntimeException this exception is thrown when the filename is empty or blank
     */
    public List<MatchDTO> extractMatches(String filename) throws RuntimeException {
        if(filename.isEmpty() || filename.isBlank()) {
            throw new RuntimeException("Cannot open CSV file with this filename!");
        }

        MatchCSVConverter matchCSVConverter = new MatchCSVConverter();
        return matchCSVConverter
                .convertToListOfModel(this.reader
                        .read(filename, MatchDTO.class));
    }

    /**
     * Extracts all PlayerDTO objects from a CSV file and returns them as a list
     * @param filename the CSV file's name
     * @return a List of PlayerDTO objects
     * @throws RuntimeException this exception is thrown when the filename is empty or blank
     */
    public List<PlayerDTO> extractPlayers(String filename) throws RuntimeException {
        if(filename.isEmpty() || filename.isBlank()) {
            throw new RuntimeException("Cannot open CSV file with this filename!");
        }

        PlayerCSVConverter playerCSVConverter = new PlayerCSVConverter();
        return playerCSVConverter
                .convertToListOfModel(this.reader
                        .read(filename, PlayerDTO.class));
    }

    /**
     * Extracts all TeamDTO objects from a CSV file and returns them as a list
     * @param filename the CSV file's name
     * @return a List of TeamDTO objects
     * @throws RuntimeException this exception is thrown when the filename is empty or blank
     */
    public List<TeamDTO> extractTeams(String filename) throws RuntimeException {
        if(filename.isEmpty() || filename.isBlank()) {
            throw new RuntimeException("Cannot open CSV file with this filename!");
        }

        TeamCSVConverter teamCSVConverter = new TeamCSVConverter();
        return teamCSVConverter
                .convertToListOfModel(this.reader
                        .read(filename, TeamDTO.class));
    }

    /**
     * Extracts all RecordDTO objects from a CSV file and returns them as a list
     * @param filename the CSV file's name
     * @return a List of RecordDTO objects
     * @throws RuntimeException this exception is thrown when the filename is empty or blank
     */
    public List<RecordDTO> extractRecords(String filename) throws RuntimeException {
        if(filename.isEmpty() || filename.isBlank()) {
            throw new RuntimeException("Cannot open CSV file with this filename!");
        }

        RecordCSVConverter recordCSVConverter = new RecordCSVConverter();
        return recordCSVConverter
                .convertToListOfModel(this.reader
                        .read(filename, RecordDTO.class));
    }
}