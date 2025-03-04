package com.example.demo.service;

import com.example.demo.dto.PeopleDTO;
import com.example.demo.entity.People;

import java.util.List;
import java.util.Optional;

public interface PeopleService {

    Optional<People> findById(int id);
    People save(PeopleDTO peopleDTO);
    People update(People people);
    void deleteById(int id);
    List<People> findAll();
}
