package com.mongoExample.mongo.service;

import com.mongoExample.mongo.dto.request.PersonRequestDto;
import com.mongoExample.mongo.dto.response.PersonResponseDto;
import com.mongoExample.mongo.exception.EntityNotFoundException;
import com.mongoExample.mongo.exception.ServiceException;
import com.mongoExample.mongo.mapper.PersonMapperService;
import com.mongoExample.mongo.model.Person;
import com.mongoExample.mongo.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements ICRUD {

    private final PersonRepository repository;
    private final PersonMapperService personMapperService;


    public PersonService(PersonRepository repository, PersonMapperService personMapperService) {
        this.repository = repository;
        this.personMapperService = personMapperService;
    }

    public PersonResponseDto create(PersonRequestDto personRequestDto) {
        try {
            Person person = personMapperService.convertToEntity(personRequestDto);
            Person savedPerson = repository.save(person);
            return personMapperService.convertToDto(savedPerson);
        } catch (ServiceException e) {
            throw new EntityNotFoundException("Error while creating the person: ", e);
        }
    }

    public PersonResponseDto update(String id, PersonRequestDto personRequestDto) {
        try {
            Person existingPerson = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Person not found with the current ID"));

            existingPerson.setName(personRequestDto.getName());
            existingPerson.setDni(personRequestDto.getDni());
            existingPerson.setSurname(personRequestDto.getSurname());
            existingPerson.setAge(personRequestDto.getAge());
            existingPerson.setAddress(personRequestDto.getAddress());

            Person updatedPerson = repository.save(existingPerson);
            return personMapperService.convertToDto(updatedPerson);
        } catch (ServiceException e) {
            throw new ServiceException("Error while updating the person: ", e);
        }
    }

    @Override
    public PersonResponseDto delete(String dni) {
        try {
            Optional<Person> person = repository.findByDni(dni);
            if (person.isPresent()) {
                repository.delete(person.get());
                return null;
            } else {
                throw new EntityNotFoundException("No se encontró la persona con el DNI: " + dni);
            }
        } catch (ServiceException e) {
            throw new ServiceException("Error al eliminar la persona: ", e);
        }
    }

    @Override
    public PersonResponseDto findOne(String dni) {
        Optional<Person> person = repository.findByDni(dni);
        if (person.isPresent()) {
            return personMapperService.convertToDto(person.get());
        } else {
            throw new ServiceException("Persona no encontrada para DNI: " + dni);
        }
    }

    @Override
    public List<PersonResponseDto> findAll() {
        List<Person> allPersons = repository.findAll();
        return personMapperService.convertToDtoAllPersons(allPersons);
    }

}
