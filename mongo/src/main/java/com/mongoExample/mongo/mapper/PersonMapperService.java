package com.mongoExample.mongo.mapper;


import com.mongoExample.mongo.dto.request.PersonRequestDto;
import com.mongoExample.mongo.dto.response.PersonResponseDto;
import com.mongoExample.mongo.model.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonMapperService {

    private final ModelMapper modelMapper;

    public PersonMapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PersonResponseDto convertToDto(Person person) {
        return modelMapper.map(person, PersonResponseDto.class);
    }

    public Person convertToEntity(PersonRequestDto personRequestDto) {
        return modelMapper.map(personRequestDto, Person.class);
    }

    public List<PersonResponseDto> convertToDtoAllPersons(List<Person> allPersons) {
        return allPersons.stream()
                .map(this::convertToDto) // Convierte cada objeto Person a PersonResponseDto
                .collect(Collectors.toList()); // Crea una lista con los objetos convertidos
    }
}

