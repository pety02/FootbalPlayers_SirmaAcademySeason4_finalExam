package com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Long, Team> {
}