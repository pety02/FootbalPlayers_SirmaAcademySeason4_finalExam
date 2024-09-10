package com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.implementations;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.EmptyDataBaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class EmptyDataBaseRepositoryImpl implements EmptyDataBaseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Counts the records in player_records table in the database
     * @return the count of the records in this table
     */
    @Transactional
    @Override
    public int countPlayerRecords() {
        return ((Number) entityManager
                .createNativeQuery("SELECT COUNT(*) FROM player_records")
                .getSingleResult()).intValue();
    }

    /**
     * Counts the records in team_matches table in the database
     * @return the count of the records in this table
     */
    @Transactional
    @Override
    public int countTeamMatches() {
        return ((Number) entityManager
                .createNativeQuery("SELECT COUNT(*) FROM team_matches")
                .getSingleResult()).intValue();
    }

    /**
     * Counts the records in team_players table in the database
     * @return the count of the records in this table
     */
    @Transactional
    @Override
    public int countTeamPlayers() {
        return ((Number) entityManager
                .createNativeQuery("SELECT COUNT(*) FROM team_players")
                .getSingleResult()).intValue();
    }
}