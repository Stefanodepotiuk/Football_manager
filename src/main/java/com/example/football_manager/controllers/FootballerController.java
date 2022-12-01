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
    public ResponseEntity<List<FootballerDTO>> getAllFootballers() {
        if (footballerService.getAll().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(footballerService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<FootballerDTO> findByIdFootballer(@PathVariable int id) {
        if (footballerService.getById(id) == null || id <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(footballerService.getById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Footballer> createFootballer(@RequestBody @Valid Footballer footballer) {
        return new ResponseEntity<>(footballerService.create(footballer), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Footballer> updateFootballer(@PathVariable int id,
                                                       @RequestBody @Valid Footballer footballer) {
        if (id <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(footballerService.update(id, footballer), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<FootballerDTO>> deleteFootballer(@PathVariable int id) {
        if (footballerService.delete(id).isEmpty() || id <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(footballerService.delete(id), HttpStatus.OK);
    }
}
