package com.example.football_manager.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Footballer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "name shouldn't be null")
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate careerStartDate;
    @ManyToOne
    private Team team;
    public Footballer(Footballer footballer) {
        this.id = footballer.getId();
        this.name = footballer.getName();
        this.dateOfBirth = footballer.getDateOfBirth();
        this.careerStartDate = footballer.getCareerStartDate();
    }

    public Footballer(int id, String name, LocalDate dateOfBirth, LocalDate careerStartDate) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.careerStartDate = careerStartDate;
    }

}
