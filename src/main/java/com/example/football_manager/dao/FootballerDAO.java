package com.example.football_manager.dao;

import com.example.football_manager.models.entity.Footballer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FootballerDAO extends JpaRepository<Footballer, Integer> {
    Footballer findFootballerById(int id);
}
