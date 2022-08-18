package com.example.football_manager.controllers;

import com.example.football_manager.models.dto.FootballerDTO;
import com.example.football_manager.models.entity.Footballer;
import com.example.football_manager.services.FootballerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/footballers")
@AllArgsConstructor
public class FootballerController {
    FootballerService footballerService;

    @GetMapping
    public ResponseEntity<List<FootballerDTO>> getAll() {
        if (footballerService.getALL().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(footballerService.getALL(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FootballerDTO> findByID(@PathVariable int id) {
        if (footballerService.getById(id) == null || id <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(footballerService.getById(id), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<FootballerDTO> create(@RequestBody @Valid Footballer footballer) {
        return new ResponseEntity<>(footballerService.createFootballer(footballer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FootballerDTO> upDate(@PathVariable int id, @RequestBody @Valid Footballer footballer) {
        if ( id <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(footballerService.UpFootballer(id, footballer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<FootballerDTO>> delete(@PathVariable int id) {
        if (footballerService.deleteFootballer(id).isEmpty() || id <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(footballerService.deleteFootballer(id), HttpStatus.OK);
    }
}
