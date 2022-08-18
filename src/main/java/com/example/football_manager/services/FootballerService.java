package com.example.football_manager.services;

import com.example.football_manager.dao.FootballerDAO;
import com.example.football_manager.models.dto.FootballerDTO;
import com.example.football_manager.models.entity.Footballer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@AllArgsConstructor
public class FootballerService {
    private FootballerDAO footballerDAO;

    public List<FootballerDTO> getALL() {
        List<Footballer> all = footballerDAO.findAll();
        return all.stream().map(FootballerDTO::new).collect(toList());
    }

    public FootballerDTO getById(int id) {
        Footballer footballer = footballerDAO.findById(id).orElse(new Footballer());
        return new FootballerDTO(footballer);
    }

    public FootballerDTO createFootballer(Footballer footballer) {
        return new FootballerDTO(footballerDAO.save(footballer));
    }

    public FootballerDTO UpFootballer(int id, Footballer footballer) {
        footballer.setId(id);
        return new FootballerDTO(footballerDAO.save(footballer));
    }

    public List<FootballerDTO> deleteFootballer(int id) {
        footballerDAO.deleteById(id);
        List<Footballer> all = footballerDAO.findAll();
        return all.stream().map(FootballerDTO::new).collect(toList());
    }

    public Footballer findOneFootballer(int id) {
        return footballerDAO.findFootballerById(id);
    }

    public double priseTransfer(int idPlayer) {
        int x = 100000;
        Footballer footballer = footballerDAO.findById(idPlayer).orElse(new Footballer());
        double experience = footballer.getExperience();
        int age = footballer.getAge();
        return x * experience / age;
    }
}
