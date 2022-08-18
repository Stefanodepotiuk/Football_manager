package com.example.football_manager.dao;

import com.example.football_manager.models.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDAO extends JpaRepository<Team, Integer> {
    Team findTeamById(int id);
}
