package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.SupportingTableDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Match;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Player;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces.SupportingService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

// TODO: to comment the class
@Component
public class SupportingTableService implements SupportingService {

    /**
     *
     * @param playerId
     * @param recordId
     */
    @Query(value = "INSERT INTO players_records (player_id, record_id) VALUES (:playerId, :recordId)", nativeQuery = true)
    private void addPlayerRecord(Long playerId, Long recordId) {
    }

    /**
     *
     * @param teamId
     * @param matchId
     */
    @Query(value = "INSERT INTO teams_matches (team_id, match_id) VALUES (:teamId, :matchId)", nativeQuery = true)
    private void addTeamMatch(Long teamId, Long matchId) {
    }

    /**
     *
     * @param teamId
     * @param playerId
     */
    @Query(value = "INSERT INTO teams_players (team_id, player_id) VALUES (:teamId, :playerId)", nativeQuery = true)
    private void addTeamPlayer(Long teamId, Long playerId) {
    }

    /**
     *
     * @param playerId
     * @param recordId
     */
    @Query(value = "UPDATE players_records SET player_id = :playerId WHERE record_id = :recordId", nativeQuery = true)
    private void updatePlayerRecord(Long playerId, Long recordId) {
    }

    /**
     *
     * @param recordId
     * @param playerId
     */
    @Query(value = "UPDATE players_records SET record_id = :recordId WHERE player_id = :playerId", nativeQuery = true)
    private void updateRecordPlayer(Long recordId, Long playerId) {
    }

    /**
     *
     * @param teamId
     * @param matchId
     */
    @Query(value = "UPDATE teams_matches SET team_id = :teamId WHERE match_id = :matchId", nativeQuery = true)
    private void updateTeamMatch(Long teamId, Long matchId) {
    }

    /**
     *
     * @param matchId
     * @param teamId
     */
    @Query(value = "UPDATE teams_matches SET match_id = :matchId WHERE team_id = :teamId", nativeQuery = true)
    private void updateMatchTeam(Long matchId, Long teamId) {
    }

    /**
     *
     * @param teamId
     * @param playerId
     */
    @Query(value = "UPDATE teams_players SET team_id = :teamId WHERE player_id = :playerId", nativeQuery = true)
    private void updateTeamPlayer(Long teamId, Long playerId) {
    }

    /**
     *
     * @param playerId
     * @param teamId
     */
    @Query(value = "UPDATE teams_players SET player_id = :playerId WHERE team_id = :teamId", nativeQuery = true)
    private void updatePlayerTeam(Long playerId, Long teamId) {
    }

    /**
     *
     * @param playerId
     * @param recordId
     */
    @Query(value = "DELETE FROM players_records WHERE player_id = :playerId AND record_id = :recordId", nativeQuery = true)
    private void deletePlayerRecord(Long playerId, Long recordId) {
    }

    /**
     *
     * @param teamId
     * @param matchId
     */
    @Query(value = "DELETE FROM teams_matches WHERE team_id = :teamId AND match_id = :matchId", nativeQuery = true)
    private void deleteTeamMatch(Long teamId, Long matchId) {
    }

    /**
     *
     * @param teamId
     * @param playerId
     */
    @Query(value = "DELETE FROM teams_players WHERE team_id = :teamId AND player_id = :playerId", nativeQuery = true)
    private void deleteTeamPlayer(Long teamId, Long playerId) {
    }

    /**
     *
     * @param firstCl
     * @param secondCl
     * @param supportingTableDTO
     * @param <M>
     * @param <N>
     * @throws RuntimeException
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
     *
     * @param firstCl
     * @param secondCl
     * @param id
     * @param supportingTableDTO
     * @param <M>
     * @param <N>
     * @throws RuntimeException
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
     *
     * @param firstCl
     * @param secondCl
     * @param firstId
     * @param secondId
     * @param <M>
     * @param <N>
     * @throws RuntimeException
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