package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.SupportingTableDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Match;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Player;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces.SupportingService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class SupportingTableService implements SupportingService {

    /**
     * Inserts into players_records supporting table new record
     * @param playerId the new record's player id
     * @param recordId the new record's record id
     */
    @Query(value = "INSERT INTO players_records (player_id, records_id) VALUES (playerId, recordId)", nativeQuery = true)
    private void addPlayerRecord(Long playerId, Long recordId) {
    }

    /**
     * Inserts into teams_matches supporting table new record
     * @param teamId the new records' team id
     * @param matchId the new record's match id
     */
    @Query(value = "INSERT INTO teams_matches (team_id, matches_id) VALUES (teamId, matchId)", nativeQuery = true)
    private void addTeamMatch(Long teamId, Long matchId) {
    }

    /**
     * Inserts into teams_players supporting table new record
     * @param teamId the new record's team id
     * @param playerId the new record's player id
     */
    @Query(value = "INSERT INTO teams_players (team_id, players_id) VALUES (teamId, playerId)", nativeQuery = true)
    private void addTeamPlayer(Long teamId, Long playerId) {
    }

    /**
     * Updates a definite record in the players_records supporting table. The updated field is player_id.
     * @param playerId the updated record's player id
     * @param recordId the updated record's record id
     */
    @Query(value = "UPDATE players_records SET player_id = playerId WHERE records_id = recordId", nativeQuery = true)
    private void updatePlayerRecord(Long playerId, Long recordId) {
    }

    /**
     * Updates a definite record in the players_records supporting table. The updated field is record_id.
     * @param recordId the updated record's record id
     * @param playerId the updated record's player id
     */
    @Query(value = "UPDATE players_records SET records_id = recordId WHERE player_id = playerId", nativeQuery = true)
    private void updateRecordPlayer(Long recordId, Long playerId) {
    }

    /**
     * Updates a definite record in the teams_matches supporting table. The updated field is team_id.
     * @param teamId the updated record's team id
     * @param matchId the updated record's match id
     */
    @Query(value = "UPDATE teams_matches SET team_id = teamId WHERE matches_id = matchId", nativeQuery = true)
    private void updateTeamMatch(Long teamId, Long matchId) {
    }

    /**
     * Updates a definite record in the teams_matches supporting table. The updated field is match_id.
     * @param matchId the updated record's match id
     * @param teamId the updated record's team id
     */
    @Query(value = "UPDATE teams_matches SET matches_id = matchId WHERE team_id = teamId", nativeQuery = true)
    private void updateMatchTeam(Long matchId, Long teamId) {
    }

    /**
     * Updates a definite record in the teams_players supporting table. The updated field is team_id.
     * @param teamId the updated record's team id
     * @param playerId the updated record's player id
     */
    @Query(value = "UPDATE teams_players SET team_id = teamId WHERE players_id = playerId", nativeQuery = true)
    private void updateTeamPlayer(Long teamId, Long playerId) {
    }

    /**
     * Updates a definite record in the teams_players supporting table. The updated field is player_id.
     * @param playerId the updated record's player id
     * @param teamId the updated record's team id
     */
    @Query(value = "UPDATE teams_players SET players_id = playerId WHERE team_id = teamId", nativeQuery = true)
    private void updatePlayerTeam(Long playerId, Long teamId) {
    }

    /**
     * Deletes a definite record from the players_records supporting table
     * @param playerId the deleted record's player id
     * @param recordId the deleted record's record id
     */
    @Query(value = "DELETE FROM players_records WHERE player_id = playerId AND records_id = recordId", nativeQuery = true)
    private void deletePlayerRecord(Long playerId, Long recordId) {
    }

    /**
     * Deletes a definite record from the teams_matches supporting table
     * @param teamId the deleted records' team id
     * @param matchId the deleted record's match id
     */
    @Query(value = "DELETE FROM teams_matches WHERE team_id = teamId AND matches_id = matchId", nativeQuery = true)
    private void deleteTeamMatch(Long teamId, Long matchId) {
    }

    /**
     * Deletes a definite record from the teams_players supporting table
     * @param teamId the deleted record's team id
     * @param playerId the deleted record's player id
     */
    @Query(value = "DELETE FROM teams_players WHERE team_id = teamId AND players_id = playerId", nativeQuery = true)
    private void deleteTeamPlayer(Long teamId, Long playerId) {
    }

    /**
     * Inserts new records in some of the supporting tables: players_records, teams_matches or teams_players.
     * The method decides which helper methods to execute as checking of firstCl and secondCl arguments values.
     * If the firstCl or secondCl has not supported values the methods throws an exception.
     * @param firstCl the first class
     * @param secondCl the second class
     * @param supportingTableDTO a SupportingTableDTO object that will be given as an argument of the helper
     *                           methods execution when its needed
     * @param <M> a template parameter that defines the value of the firstCl
     * @param <N> a template parameter that defines the value of the secondCl
     * @throws RuntimeException this exception is thrown when any of the template argument defines not
     * supported type for firstCl or secondCl parameters. The supported types are: Player.class, Record.class,
     * Team.class or Match.class.
     */
    @Override
    public <M, N> void create(Class<M> firstCl, Class<N> secondCl,
                                            SupportingTableDTO supportingTableDTO) throws RuntimeException {
        if((firstCl == Player.class && secondCl == Record.class)
                || (firstCl == Record.class && secondCl == Player.class)) {
            addPlayerRecord(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else if ((firstCl == Team.class && secondCl == Match.class)
                || (firstCl == Match.class && secondCl == Team.class)) {
            addTeamMatch(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else if ((firstCl == Team.class && secondCl == Player.class)
                || (firstCl == Player.class && secondCl == Team.class)) {
            addTeamPlayer(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else {
            throw new RuntimeException("Not supported class types!");
        }
    }

    /**
     * Updates a definite record in some of the supporting tables: players_records, teams_matches or teams_players.
     * The method decides which helper methods to execute as checking of firstCl and secondCl arguments values.
     * If the firstCl or secondCl has not supported values the methods throws an exception.
     * @param firstCl the first class
     * @param secondCl the second class
     * @param id the id for the updated record
     * @param supportingTableDTO a SupportingTableDTO object that will be given as an argument of the helper
     *                           methods execution when its needed
     * @param <M> a template parameter that defines the value of the firstCl
     * @param <N> a template parameter that defines the value of the secondCl
     * @throws RuntimeException this exception is thrown when any of the template argument defines not
     * supported type for firstCl or secondCl parameters. The supported types are: Player.class, Record.class,
     * Team.class or Match.class.
     */
    @Override
    public <M, N> void update(Class<M> firstCl, Class<N> secondCl, Long id,
                              SupportingTableDTO supportingTableDTO) throws RuntimeException {
        if(firstCl == Player.class && secondCl == Record.class) {
            updatePlayerRecord(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else if (firstCl == Record.class && secondCl == Player.class) {
            updateRecordPlayer(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else if (firstCl == Team.class && secondCl == Match.class) {
            updateTeamMatch(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else if (firstCl == Match.class && secondCl == Team.class) {
            updateMatchTeam(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else if (firstCl == Team.class && secondCl == Player.class) {
            updateTeamPlayer(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else if (firstCl == Player.class && secondCl == Team.class) {
            updatePlayerTeam(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else {
            throw new RuntimeException("Not supported class types!");
        }
    }

    /**
     * Deletes from the supporting tables: players_records, teams_matches or teams_players.
     * The method decides which helper methods to execute as checking of firstCl and secondCl arguments values.
     * If the firstCl or secondCl has not supported values the methods throws an exception.
     * @param firstCl the first class
     * @param secondCl the second class
     * @param firstId the first id, passed as an argument to the helpers methods
     * @param secondId the second id, passed as an argument to the helpers methods
     * @param <M> a template parameter that defines the value of the firstCl
     * @param <N> a template parameter that defines the value of the secondCl
     * @throws RuntimeException this exception is thrown when any of the template argument defines not
     * supported type for firstCl or secondCl parameters. The supported types are: Player.class, Record.class,
     * Team.class or Match.class.
     */
    @Override
    public <M, N> void deleteById(Class<M> firstCl, Class<N> secondCl, Long firstId, Long secondId) throws RuntimeException {
        if((firstCl == Player.class && secondCl == Record.class)
                || (firstCl == Record.class && secondCl == Player.class)) {
            deletePlayerRecord(firstId, secondId);
        } else if ((firstCl == Team.class && secondCl == Match.class)
                || (firstCl == Match.class && secondCl == Team.class)) {
            deleteTeamMatch(firstId, secondId);
        } else if ((firstCl == Team.class && secondCl == Player.class)
                || (firstCl == Player.class && secondCl == Team.class)) {
            deleteTeamPlayer(firstId, secondId);
        } else {
            throw new RuntimeException("Not supported class types!");
        }
    }
}