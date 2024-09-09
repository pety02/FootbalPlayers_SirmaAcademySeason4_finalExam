package com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmptyDataBaseRepository {

    @Query(value = "SELECT COUNT(*) FROM player_records", nativeQuery = true)
    int countPlayerRecords();
    @Query(value = "SELECT COUNT(*) FROM team_matches", nativeQuery = true)
    int countTeamMatches();
    @Query(value = "SELECT COUNT(*) FROM team_players", nativeQuery = true)
    int countTeamPlayers();
}