package com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class EmptyDataBaseRepositoryImpl implements EmptyDataBaseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public int countPlayerRecords() {
        return ((Number) entityManager
                .createNativeQuery("SELECT COUNT(*) FROM player_records")
                .getSingleResult()).intValue();
    }

    @Transactional
    @Override
    public int countTeamMatches() {
        return ((Number) entityManager
                .createNativeQuery("SELECT COUNT(*) FROM team_matches")
                .getSingleResult()).intValue();
    }

    @Transactional
    @Override
    public int countTeamPlayers() {
        return ((Number) entityManager
                .createNativeQuery("SELECT COUNT(*) FROM team_players")
                .getSingleResult()).intValue();
    }
}