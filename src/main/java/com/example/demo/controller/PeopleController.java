package com.example.demo.controller;

import com.example.demo.dto.PeopleDTO;
import com.example.demo.entity.People;
import com.example.demo.exception.InternalServerErrorException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.PeopleMapper;
import com.example.demo.service.PeopleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final PeopleMapper peopleMapper;

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<PeopleDTO>> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(Optional.ofNullable(peopleMapper.toDTO(peopleService.findById(id).orElseThrow(() -> new NotFoundException("People #ID:" + id + " not found")))));
    }

    @PostMapping("/add")
    public ResponseEntity<String> save(@RequestBody PeopleDTO peopleDTO) {
        peopleService.save(peopleDTO) ;
        return ResponseEntity.ok("People #NAME: " + peopleDTO.getName() + " created.");

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<People> update(@PathVariable("id") int id, @RequestBody People people) {

        People people1 = peopleService.findById(id).orElseThrow(() -> new NotFoundException("People #ID:" + id + " does not exist"));

        people.setId(people1.getId());
        people1.setName(people.getName());
        people1.setAge(people.getAge());
        people1.setEmail(people.getEmail());

        return ResponseEntity.ok(peopleService.update(people));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        peopleService.deleteById(id);
        return ResponseEntity.ok("People #ID: " + id + " deleted.");
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<PeopleDTO>> findAll() {
        return ResponseEntity.ok(peopleMapper.toDTOList(peopleService.findAll()));
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(InternalServerErrorException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(NotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}