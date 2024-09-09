package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters.MatchAdapter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters.PlayerAdapter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters.RecordAdapter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters.TeamAdapter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.MatchDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.PlayerDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.RecordDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.TeamDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Match;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Player;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Record;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.converters.MatchCSVConverter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.converters.PlayerCSVConverter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.converters.RecordCSVConverter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.converters.TeamCSVConverter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Component
@Validated
public class CSVDataExtractor {
    private final Reader reader;
    private final MatchAdapter matchAdapter;
    private final PlayerAdapter playerAdapter;
    private final TeamAdapter teamAdapter;
    private final RecordAdapter recordAdapter;

    /**
     * CSVDataExtractor class constructor with parameters
     * @param matchAdapter the match adapter that converts Match object to MatchDTO object and vice versa
     * @param playerAdapter the player adapter that converts Player object to PlayerDTO object and vice versa
     * @param teamAdapter the team adapter that converts Team object to TeamDTO object and vice versa
     * @param recordAdapter the record adapter that converts Record object to RecordDTO object and vice versa
     */
    @Autowired
    public CSVDataExtractor(@NonNull MatchAdapter matchAdapter,
                            @NonNull PlayerAdapter playerAdapter,
                            @NonNull TeamAdapter teamAdapter,
                            @NonNull RecordAdapter recordAdapter) {
        this.reader = new Reader();
        this.matchAdapter = matchAdapter;
        this.playerAdapter = playerAdapter;
        this.teamAdapter = teamAdapter;
        this.recordAdapter = recordAdapter;
    }

    /**
     * Extracts all MatchDTO objects from a CSV file, converts all of them into Match objects
     * and return them as a list
     * @param filename the CSV file's name
     * @return a List of Match objects
     * @throws RuntimeException this exception is thrown when the filename is empty or blank
     */
    public List<Match> extractMatches(String filename) throws RuntimeException {
        if(filename.isEmpty() || filename.isBlank()) {
            throw new RuntimeException("Cannot open CSV file with this filename!");
        }
        MatchCSVConverter matchCSVConverter = new MatchCSVConverter();
        List<MatchDTO> matchDTOs = matchCSVConverter
                .convertToListOfModel(this.reader
                        .read(filename, MatchDTO.class));

        List<Match> matches = new ArrayList<>();
        for(MatchDTO matchDTO : matchDTOs) {
            matches.add(this.matchAdapter.toEntity(matchDTO));
        }

        return matches;
    }

    /**
     * Extracts all PlayerDTO objects from a CSV file, converts all of them into Player objects
     * and return them as a list
     * @param filename the CSV file's name
     * @return a List of Player objects
     * @throws RuntimeException this exception is thrown when the filename is empty or blank
     */
    public List<Player> extractPlayers(String filename) throws RuntimeException {
        if(filename.isEmpty() || filename.isBlank()) {
            throw new RuntimeException("Cannot open CSV file with this filename!");
        }
        PlayerCSVConverter playerCSVConverter = new PlayerCSVConverter();
        List<PlayerDTO> playerDTOs = playerCSVConverter
                .convertToListOfModel(this.reader
                        .read(filename, PlayerDTO.class));

        List<Player> players = new ArrayList<>();
        for(PlayerDTO playerDTO : playerDTOs) {
            players.add(this.playerAdapter.toEntity(playerDTO));
        }

        return players;
    }

    /**
     * Extracts all TeamDTO objects from a CSV file, converts all of them into Team objects
     * and return them as a list
     * @param filename the CSV file's name
     * @return a List of Team objects
     * @throws RuntimeException this exception is thrown when the filename is empty or blank
     */
    public List<Team> extractTeams(String filename) throws RuntimeException {
        if(filename.isEmpty() || filename.isBlank()) {
            throw new RuntimeException("Cannot open CSV file with this filename!");
        }
        TeamCSVConverter teamCSVConverter = new TeamCSVConverter();
        List<TeamDTO> teamDTOs = teamCSVConverter
                .convertToListOfModel(this.reader
                        .read(filename, TeamDTO.class));

        List<Team> teams = new ArrayList<>();
        for(TeamDTO teamDTO : teamDTOs) {
            teams.add(this.teamAdapter.toEntity(teamDTO));
        }

        return teams;
    }

    /**
     * Extracts all RecordDTO objects from a CSV file, converts all of them into Record objects
     * and return them as a list
     * @param filename the CSV file's name
     * @return a List of Record objects
     * @throws RuntimeException this exception is thrown when the filename is empty or blank
     */
    public List<Record> extractRecords(String filename) throws RuntimeException {
        if(filename.isEmpty() || filename.isBlank()) {
            throw new RuntimeException("Cannot open CSV file with this filename!");
        }
        RecordCSVConverter recordCSVConverter = new RecordCSVConverter();
        List<RecordDTO> recordDTOs = recordCSVConverter
                .convertToListOfModel(this.reader
                        .read(filename, RecordDTO.class));

        List<Record> records = new ArrayList<>();
        for(RecordDTO recordDTO : recordDTOs) {
            records.add(this.recordAdapter.toEntity(recordDTO));
        }

        return records;
    }
}