package com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findById(Long id);
}