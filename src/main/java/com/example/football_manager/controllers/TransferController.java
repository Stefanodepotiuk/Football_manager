package com.example.football_manager.controllers;

import com.example.football_manager.services.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfers")
@AllArgsConstructor
public class TransferController {
    TransferService transferService;
    @GetMapping("/{idTeamSeller}/{idPlayer}/{idTeamCustomer}")
    public ResponseEntity<String> transfer(@PathVariable int idTeamSeller, @PathVariable int idPlayer,
                                           @PathVariable int idTeamCustomer) {
        if (idTeamSeller <= 0 || idPlayer <= 0 || idTeamCustomer <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(transferService.transfer(idTeamSeller, idPlayer, idTeamCustomer), HttpStatus.OK);
    }
}
