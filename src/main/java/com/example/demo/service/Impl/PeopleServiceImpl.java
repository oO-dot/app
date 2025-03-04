package com.example.demo.service.Impl;

import com.example.demo.dto.PeopleDTO;
import com.example.demo.entity.People;
import com.example.demo.repository.PeopleRepository;
import com.example.demo.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class PeopleServiceImpl implements PeopleService {

    private final PeopleRepository peopleRepository;

    @Override
    public Optional<People> findById(int id) {
        return peopleRepository.findById(id);
    }

    @Override
    public People save(PeopleDTO peopleDTO) {
        return peopleRepository.save(People.builder()
                .name(peopleDTO.getName())
                .age(peopleDTO.getAge())
                .email(peopleDTO.getEmail())
                .build());
    }

    @Override
    public People update(People people) {
        return peopleRepository.save(people);
    }

    @Override
    public void deleteById(int id) {
        peopleRepository.deleteById(id);
    }

    @Override
    public List<People> findAll() {
        return peopleRepository.findAll();
    }
}
