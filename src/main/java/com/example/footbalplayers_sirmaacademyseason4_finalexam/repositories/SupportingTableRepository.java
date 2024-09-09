package com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportingTableRepository {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO player_records (player_id, records_id) VALUES (?1, ?2)", nativeQuery = true)
    int addPlayerRecord(Long playerId, Long recordId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO team_matches (team_id, matches_id) VALUES (?1, ?2)", nativeQuery = true)
    int addTeamMatch(Long teamId, Long matchId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO team_players (team_id, players_id) VALUES (?1, ?2)", nativeQuery = true)
    int addTeamPlayer(Long teamId, Long playerId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE player_records SET player_id = ?1 WHERE records_id = ?2", nativeQuery = true)
    int updatePlayerRecord(Long playerId, Long recordId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE player_records SET records_id = ?1 WHERE player_id = ?2", nativeQuery = true)
    int updateRecordPlayer(Long recordId, Long playerId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE team_matches SET team_id = ?1 WHERE matches_id = ?2", nativeQuery = true)
    int updateTeamMatch(Long teamId, Long matchId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE team_matches SET matches_id = ?1 WHERE team_id = ?2", nativeQuery = true)
    int updateMatchTeam(Long matchId, Long teamId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE team_players SET team_id = ?1 WHERE players_id = ?2", nativeQuery = true)
    int updateTeamPlayer(Long teamId, Long playerId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE team_players SET players_id = ?1 WHERE team_id = ?2", nativeQuery = true)
    int updatePlayerTeam(Long playerId, Long teamId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM player_records WHERE player_id = ?1 AND records_id = ?2", nativeQuery = true)
    int deletePlayerRecord(Long playerId, Long recordId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM team_matches WHERE team_id = ?1 AND matches_id = ?2", nativeQuery = true)
    int deleteTeamMatch(Long teamId, Long matchId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM team_players WHERE team_id = ?1 AND players_id = ?2", nativeQuery = true)
    int deleteTeamPlayer(Long teamId, Long playerId);
}