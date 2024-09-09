package com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface SupportingTableRepository {
    int addPlayerRecord(Long playerId, Long recordId);
    int addTeamMatch(Long teamId, Long matchId);
    int addTeamPlayer(Long teamId, Long playerId);
    int updatePlayerRecord(Long playerId, Long recordId);
    int updateRecordPlayer(Long recordId, Long playerId);
    int updateTeamMatch(Long teamId, Long matchId);
    int updateMatchTeam(Long matchId, Long teamId);
    int updateTeamPlayer(Long teamId, Long playerId);
    int updatePlayerTeam(Long playerId, Long teamId);
    int deletePlayerRecord(Long playerId, Long recordId);
    int deleteTeamMatch(Long teamId, Long matchId);
    int deleteTeamPlayer(Long teamId, Long playerId);
}