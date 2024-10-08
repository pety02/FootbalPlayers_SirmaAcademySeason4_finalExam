package com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.implementations;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.SupportingTableRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class SupportingTableRepositoryImpl implements SupportingTableRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Adds a record in the player_records table in the database
     * @param playerId the id of the player
     * @param recordId the id of the record
     * @return the count of rows in player_records table that were affected
     * of the query
     */
    @Transactional
    @Override
    public int addPlayerRecord(Long playerId, Long recordId) {
        return entityManager
                .createNativeQuery("INSERT INTO player_records (player_id, records_id) VALUES (?1, ?2)")
                .setParameter(1, playerId)
                .setParameter(2, recordId)
                .executeUpdate();
    }

    /**
     * Adds a record in the team_matches table in the database
     * @param teamId the id of the team
     * @param matchId the id of the match
     * @return the count of rows in team_Matches table that were affected
     * of the query
     */
    @Transactional
    @Override
    public int addTeamMatch(Long teamId, Long matchId) {
        return entityManager
                .createNativeQuery("INSERT INTO team_matches (team_id, matches_id) VALUES (?1, ?2)")
                .setParameter(1, teamId)
                .setParameter(2, matchId)
                .executeUpdate();
    }

    /**
     * Adds a record in the team_players table in the database
     * @param teamId the id of the team
     * @param playerId the id of the player
     * @return the count of rows in team_players table that were affected
     * of the query
     */
    @Transactional
    @Override
    public int addTeamPlayer(Long teamId, Long playerId) {
        return entityManager
                .createNativeQuery("INSERT INTO team_players (team_id, players_id) VALUES (?1, ?2)")
                .setParameter(1, teamId)
                .setParameter(2, playerId)
                .executeUpdate();
    }

    /**
     * Updates a record in the player_records table in the database
     * @param playerId the id of the player
     * @param recordId the id of the record
     * @return the count of rows in player_records table that were affected
     * of the query
     */
    @Transactional
    @Override
    public int updatePlayerRecord(Long recordId, Long playerId) {
        return entityManager
                .createNativeQuery("UPDATE player_records SET player_id = ?1 WHERE records_id = ?2")
                .setParameter(1, playerId)
                .setParameter(2, recordId)
                .executeUpdate();
    }

    /**
     * Updates a record in the player_records table in the database
     * @param playerId the id of the player
     * @param recordId the id of the record
     * @return the count of rows in player_records table that were affected
     * of the query
     */
    @Override
    public int updateRecordPlayer(Long recordId, Long playerId) {
        return entityManager
                .createNativeQuery("UPDATE player_records SET records_id = ?1 WHERE player_id = ?2")
                .setParameter(1, recordId)
                .setParameter(2, playerId)
                .executeUpdate();
    }

    /**
     * Updates a record in the team_matches table in the database
     * @param teamId the id of the team
     * @param matchId the id of the match
     * @return the count of rows in team_Matches table that were affected
     * of the query
     */
    @Transactional
    @Override
    public int updateTeamMatch(Long teamId, Long matchId) {
        return entityManager
                .createNativeQuery("UPDATE team_matches SET team_id = ?1 WHERE matches_id = ?2")
                .setParameter(1, teamId)
                .setParameter(2, matchId)
                .executeUpdate();
    }

    /**
     * Updates a record in the team_matches table in the database
     * @param teamId the id of the team
     * @param matchId the id of the match
     * @return the count of rows in team_Matches table that were affected
     * of the query
     */
    @Override
    public int updateMatchTeam(Long matchId, Long teamId) {
        return entityManager
                .createNativeQuery("UPDATE team_matches SET matches_id = ?1 WHERE team_id = ?2")
                .setParameter(1, matchId)
                .setParameter(2, teamId)
                .executeUpdate();
    }

    /**
     * Updates a record in the team_players table in the database
     * @param teamId the id of the team
     * @param playerId the id of the player
     * @return the count of rows in team_players table that were affected
     * of the query
     */
    @Transactional
    @Override
    public int updateTeamPlayer(Long teamId, Long playerId) {
        return entityManager
                .createNativeQuery("UPDATE team_players SET team_id = ?1 WHERE players_id = ?2")
                .setParameter(1, teamId)
                .setParameter(2, playerId)
                .executeUpdate();
    }

    /**
     * Updates a record in the team_players table in the database
     * @param teamId the id of the team
     * @param playerId the id of the player
     * @return the count of rows in team_players table that were affected
     * of the query
     */
    @Override
    public int updatePlayerTeam(Long playerId, Long teamId) {
        return entityManager
                .createNativeQuery("UPDATE team_players SET players_id = ?1 WHERE team_id = ?2")
                .setParameter(1, playerId)
                .setParameter(2, teamId)
                .executeUpdate();
    }

    /**
     * Deletes a record in the player_records table in the database
     * @param playerId the id of the player
     * @param recordId the id of the record
     * @return the count of rows in player_records table that were affected
     * of the query
     */
    @Transactional
    @Override
    public int deletePlayerRecord(Long playerId, Long recordId) {
        return entityManager
                .createNativeQuery("DELETE FROM player_records WHERE player_id = ?1 AND records_id = ?2")
                .setParameter(1, playerId)
                .setParameter(2, recordId)
                .executeUpdate();
    }

    /**
     * Deletes a record in the team_matches table in the database
     * @param teamId the id of the team
     * @param matchId the id of the match
     * @return the count of rows in team_Matches table that were affected
     * of the query
     */
    @Transactional
    @Override
    public int deleteTeamMatch(Long teamId, Long matchId) {
        return entityManager
                .createNativeQuery("DELETE FROM team_matches WHERE team_id = ?1 AND matches_id = ?2")
                .setParameter(1, teamId)
                .setParameter(2, matchId)
                .executeUpdate();
    }

    /**
     * Deletes a record in the team_players table in the database
     * @param teamId the id of the team
     * @param playerId the id of the player
     * @return the count of rows in team_players table that were affected
     * of the query
     */
    @Transactional
    @Override
    public int deleteTeamPlayer(Long teamId, Long playerId) {
        return entityManager
                .createNativeQuery("DELETE FROM team_players WHERE team_id = ?1 AND players_id = ?2")
                .setParameter(1, teamId)
                .setParameter(2, playerId)
                .executeUpdate();
    }
}