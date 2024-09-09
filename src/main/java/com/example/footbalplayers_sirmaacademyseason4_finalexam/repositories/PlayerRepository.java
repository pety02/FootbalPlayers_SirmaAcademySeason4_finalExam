package com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findById(Long id);
    @Query(value = "SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Player p WHERE p.teamNumber = :teamNumber")
    boolean existsByTeamNumber(Integer teamNumber);
}