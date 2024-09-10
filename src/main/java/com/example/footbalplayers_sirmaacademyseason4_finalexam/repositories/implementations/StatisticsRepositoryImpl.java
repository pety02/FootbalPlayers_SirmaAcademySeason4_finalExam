package com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.implementations;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.StatisticsDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.StatisticsRepository;
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

    /**
     * Selects all players that are played for the longest time (in minutes) in same match,
     * are from different teams and also the duration of their common play in the match.
     * The query is select query from record and player tables in the database and the
     * result sets are joined on their primary keys so that to return the right result set
     * of players.
     * @return a List of StatisticsDTO objects where StatisticsDTO is a data transfer object
     * that contains the definite match id, the full names of the pair of players that are
     * played together and the duration of their common play
     */
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