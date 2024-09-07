package com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Long, Player> {
}