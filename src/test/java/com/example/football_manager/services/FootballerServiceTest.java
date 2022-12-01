package com.example.football_manager.services;

import com.example.football_manager.dao.FootballerDAO;
import com.example.football_manager.models.dto.FootballerDTO;
import com.example.football_manager.models.entity.Footballer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FootballerServiceTest {
    @Mock
    FootballerDAO footballerDAO;
    @InjectMocks
    FootballerService footballerService;
    Footballer footballer;
    @BeforeEach
    public void prepareData(){
         footballer = new Footballer(1, "Cristiano Ronaldo", LocalDate.parse("1990-10-10"),
                LocalDate.parse("1990-10-10"));
    }
    @Test
    void getAll() {
        //setup mock data (order 3)
        ArrayList<Footballer> listFootballers = new ArrayList<>();
        listFootballers
                .add(new Footballer(1, "Ronaldo", LocalDate.parse("1990-10-10"),
                        LocalDate.parse("1990-10-10")));
        listFootballers
                .add(new Footballer(2, "Messi", LocalDate.parse("1992-05-05")
                        , LocalDate.parse("2002-05-05")));

        ArrayList<FootballerDTO> expected = new ArrayList<>();
        expected
                .add(new FootballerDTO(1, "Ronaldo", LocalDate.parse("1990-10-10"),
                        LocalDate.parse("1990-10-10")));
        expected
                .add(new FootballerDTO(2, "Messi", LocalDate.parse("1992-05-05")
                        , LocalDate.parse("2002-05-05")));

        //mock methods (order 2)
        when(footballerDAO.findAll()).thenReturn(listFootballers);

        //call tested method (order 1)
        List<FootballerDTO> result = footballerService.getAll();

        //asserts (order 4)
        assertEquals(expected, result);

        verify(footballerDAO).findAll();

    }

    @Test
    void getById() {
        when(footballerDAO.findById(1)).thenReturn(Optional.of(footballer));
        FootballerDTO result = footballerService.getById(1);

        assertEquals(footballer.getId(),result.getId());
        assertEquals(footballer.getName(),result.getName());
        assertEquals(footballer.getDateOfBirth(),result.getDateOfBirth());
        assertEquals(footballer.getCareerStartDate(),result.getCareerStartDate());

        verify(footballerDAO).findById(1);
    }

    @Test
    void create() {
        when(footballerDAO.save(footballer)).thenReturn(footballer);

        Footballer result = footballerService.create(footballer);

        assertEquals(footballer.getId(),result.getId());
        assertEquals(footballer.getName(),result.getName());
        assertEquals(footballer.getDateOfBirth(),result.getDateOfBirth());
        assertEquals(footballer.getCareerStartDate(),result.getCareerStartDate());

        verify(footballerDAO).save(footballer);

    }

    @Test
    void update() {
        when(footballerDAO.save(footballer)).thenReturn(footballer);
        Footballer result = footballerService.update(1,footballer);

        assertEquals(footballer.getId(),result.getId());
        assertEquals(footballer.getName(),result.getName());
        assertEquals(footballer.getDateOfBirth(),result.getDateOfBirth());
        assertEquals(footballer.getCareerStartDate(),result.getCareerStartDate());
    }

    @Test
    void findOneFootballer() {
        when(footballerDAO.findFootballerById(1)).thenReturn(footballer);
        Footballer result = footballerService.findOneFootballer(1);

        assertEquals(footballer.getId(),result.getId());
        assertEquals(footballer.getName(),result.getName());
        assertEquals(footballer.getDateOfBirth(),result.getDateOfBirth());
        assertEquals(footballer.getCareerStartDate(),result.getCareerStartDate());
    }
}