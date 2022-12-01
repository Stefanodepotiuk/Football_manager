package com.example.football_manager.models.dto;

import com.example.football_manager.models.entity.Footballer;
import com.example.football_manager.models.entity.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class FootballerDTO {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private LocalDate careerStartDate;
    private Team team;

    public FootballerDTO(Footballer footballer) {
        this.id = footballer.getId();
        this.name = footballer.getName();
        this.dateOfBirth = footballer.getDateOfBirth();
        this.careerStartDate = footballer.getCareerStartDate();
    }

    public FootballerDTO(int id, String name, LocalDate dateOfBirth, LocalDate careerStartDate) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.careerStartDate = careerStartDate;
    }
}