package com.example.football_manager.services;

import com.example.football_manager.dao.FootballerDAO;
import com.example.football_manager.models.dto.FootballerDTO;
import com.example.football_manager.models.entity.Footballer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class FootballerService {
    private FootballerDAO footballerDAO;
    public List<FootballerDTO> getAll() {
        List<Footballer> all = footballerDAO.findAll();
        return all.stream().map(FootballerDTO::new).collect(toList());
    }
    public FootballerDTO getById(int id) {
        Footballer footballer = footballerDAO.findById(id)
                .orElseThrow(() -> new NullPointerException("This footballer isn't exist"));
        return new FootballerDTO(footballer);
    }
    public Footballer create(Footballer footballer) {
        return new Footballer(footballerDAO.save(footballer));
    }
    public Footballer update(int id, Footballer footballer) {
        footballer.setId(id);
        return new Footballer(footballerDAO.save(footballer));
    }
    public List<FootballerDTO> delete(int id) {
        footballerDAO.deleteById(id);
        List<Footballer> all = footballerDAO.findAll();
        return all.stream().map(FootballerDTO::new).collect(toList());
    }
    public Footballer findOneFootballer(int id) throws NullPointerException {
        return footballerDAO.findFootballerById(id);
    }
}
