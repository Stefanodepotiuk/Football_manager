package com.example.football_manager.dao;

import com.example.football_manager.models.entity.Footballer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FootballerDAO extends JpaRepository<Footballer, Integer> {
    List<Footballer> findFootballersById(int id);

    Footballer findFootballerById(int id);

}
