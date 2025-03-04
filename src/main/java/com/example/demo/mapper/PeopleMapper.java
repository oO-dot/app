package com.example.demo.mapper;

import com.example.demo.dto.PeopleDTO;
import com.example.demo.entity.People;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeopleMapper {

    PeopleDTO toDTO(People people);
    People toEntity(PeopleDTO peopleDTO);

    List<PeopleDTO> toDTOList(List<People> peopleList);
}
