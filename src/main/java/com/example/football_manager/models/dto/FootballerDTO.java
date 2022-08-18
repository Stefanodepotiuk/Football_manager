package com.example.football_manager.models.dto;

import com.example.football_manager.models.entity.Footballer;
import com.example.football_manager.models.entity.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FootballerDTO {
    private int id;
    private String name;
    private int age;
    private double experience;

    private Team team;

    public FootballerDTO(Footballer footballer) {
        this.id = footballer.getId();
        this.name = footballer.getName();
        this.age = footballer.getAge();
        this.experience = footballer.getExperience();
    }

    public FootballerDTO(Team team) {

    }

    public double priseTransfer(){
        return (experience * 100000)/age;
    }
}
