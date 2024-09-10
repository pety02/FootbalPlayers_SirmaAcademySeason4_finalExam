package com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.StatisticsDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StatisticsRepositoryImpl implements StatisticsRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    @Override
    public List<StatisticsDTO> loadAll() {
        List<Object[]> objs = entityManager
                .createNativeQuery("SELECT matchId, player1, player2, totalOverlap\n" +
                        "FROM (\n" +
                        "    SELECT \n" +
                        "        rp1.match_id AS matchId,\n" +
                        "        player1.full_name AS player1,\n" +
                        "        player2.full_name AS player2,\n" +
                        "        (LEAST(COALESCE(rp1.to_minutes, 90), COALESCE(rp2.to_minutes, 90)) - \n" +
                        "        GREATEST(rp1.from_minutes, rp2.from_minutes)) AS totalOverlap\n" +
                        "    FROM \n" +
                        "        record rp1\n" +
                        "    JOIN \n" +
                        "        record rp2 \n" +
                        "        ON rp1.match_id = rp2.match_id\n" +
                        "        AND rp1.player_id != rp2.player_id\n" +
                        "        AND rp1.player_id < rp2.player_id\n" +
                        "    JOIN \n" +
                        "        player player1\n" +
                        "        ON player1.id = rp1.player_id\n" +
                        "    JOIN \n" +
                        "        player player2\n" +
                        "        ON player2.id = rp2.player_id\n" +
                        "    WHERE \n" +
                        "        player1.team_id != player2.team_id\n" +
                        ") AS subquery\n" +
                        "WHERE totalOverlap > 0\n" +
                        "ORDER BY totalOverlap DESC, player1 ASC;\n")
                .getResultList();

        List<StatisticsDTO> statisticsDTOs = new ArrayList<>();
        for(Object[] row : objs) {
            long id = ((Number) row[0]).longValue();
            String playerAName = (String) row[1];
            String playerBName = (String) row[2];
            int minutes = ((Number) row[3]).intValue();
            StatisticsDTO statisticsDTO = new StatisticsDTO(id, playerAName, playerBName, minutes);
            statisticsDTOs.add(statisticsDTO);
        }

        return statisticsDTOs;
    }
}