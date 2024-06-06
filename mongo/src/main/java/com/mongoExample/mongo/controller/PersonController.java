package com.mongoExample.mongo.controller;

import com.mongoExample.mongo.dto.request.PersonRequestDto;
import com.mongoExample.mongo.dto.response.PersonResponseDto;
import com.mongoExample.mongo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public ResponseEntity<PersonResponseDto> create(@RequestBody PersonRequestDto REQUEST) {
        logger.info("Entering create method");
        PersonResponseDto RESPONSE = personService.create(REQUEST);
        logger.info("Exiting create method");
        return new ResponseEntity<>(RESPONSE, HttpStatus.CREATED);
    }

    @PutMapping("/update/{dni}")
    public ResponseEntity<PersonResponseDto> update(@PathVariable String id, PersonRequestDto REQUEST) {
        logger.info("Entering update method");
        PersonResponseDto RESPONSE = personService.update(id, REQUEST);
        logger.info("Exiting update method");
        return new ResponseEntity<>(RESPONSE, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{dni}")
    public ResponseEntity<PersonResponseDto> delete(@PathVariable String dni) {
        logger.info("Entering delete method");
        PersonResponseDto deletedPerson = personService.delete(dni);
        logger.info("Exiting delete method");
        return new ResponseEntity<>(deletedPerson, HttpStatus.NO_CONTENT); //  TESTEAR
    }


    @GetMapping("/findAll")
    public ResponseEntity<List<PersonResponseDto>> findAll() {
        logger.info("Entering findAll method");
        List<PersonResponseDto> persons = personService.findAll();
        logger.info("Exiting findAll method");
        return new ResponseEntity<>(persons, HttpStatus.FOUND);
    }

    @GetMapping("/find/{dni}")
    public ResponseEntity<PersonResponseDto> findOne(@PathVariable String dni) {
        logger.info("Entering findOne method");
        return new ResponseEntity<>(personService.findOne(dni), HttpStatus.OK);
    }
}
