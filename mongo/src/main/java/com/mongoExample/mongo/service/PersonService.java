package com.mongoExample.mongo.service;

import com.mongoExample.mongo.dto.request.PersonRequestDto;
import com.mongoExample.mongo.dto.response.PersonResponseDto;
import com.mongoExample.mongo.exception.PersonServiceException;
import com.mongoExample.mongo.mapper.PersonMapperService;
import com.mongoExample.mongo.model.Person;
import com.mongoExample.mongo.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository repository;
    private final PersonMapperService personMapperService;

    //TO-DO: LA INTERACCIÓN DEBE SER CON LOS DTO´S


    public PersonService(PersonRepository repository, PersonMapperService personMapperService) {
        this.repository = repository;
        this.personMapperService = personMapperService;
    }


    public PersonResponseDto create(PersonRequestDto personRequestDto) {
        try {
            Person person = personMapperService.convertToEntity(personRequestDto);
            Person savedPerson = repository.save(person);
            return personMapperService.convertToDto(savedPerson);
        } catch (Exception e) {
            throw new PersonServiceException("Error while creating the person: ", e);
        }
    }

    public PersonResponseDto update(String id, PersonRequestDto personRequestDto) {
        try {
            Person existingPerson = repository.findById(id)
                    .orElseThrow(() -> new PersonServiceException("Person not found with the current ID"));
            // Actualizar atributos
            existingPerson.setName(personRequestDto.getName());
            existingPerson.setSurname(personRequestDto.getSurname());
            existingPerson.setAge(personRequestDto.getAge());
            existingPerson.setAddress(personRequestDto.getAddress());

            Person updatedPerson = repository.save(existingPerson);
            return personMapperService.convertToDto(updatedPerson);
        } catch (Exception e) {
            throw new PersonServiceException("Error while updating the person: ", e);
        }
    }

    public void delete(String id) {
        try {
            repository.deleteById(id);
        } catch (PersonServiceException e) {
            throw new PersonServiceException("Can´t found the person with the current ID: ", e);
        }

    }

    public PersonResponseDto findOne(String id) {
        Optional<Person> person = repository.findById(id);
        return personMapperService.convertToDto(person.orElse(null));
    }

    public List<PersonResponseDto> findAll() {
        List<Person> allPersons = repository.findAll();
        return personMapperService.convertToDtoAllPersons(allPersons);
    }
}
