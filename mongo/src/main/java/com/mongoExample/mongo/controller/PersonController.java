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

    @PutMapping("/update/{id}")
    public ResponseEntity<PersonResponseDto> update(@PathVariable String id, PersonRequestDto REQUEST) {
        logger.info("Entering update method");
        PersonResponseDto RESPONSE = personService.update(id, REQUEST);
        logger.info("Exiting update method");
        return new ResponseEntity<>(RESPONSE, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PersonResponseDto> delete(@PathVariable String id) {
        logger.info("Entering delete method");
        personService.delete(id);
        logger.info("Exiting delete method");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<PersonResponseDto>> findAll() {
        logger.info("Entering findAll method");
        List<PersonResponseDto> persons = personService.findAll();
        logger.info("Exiting findAll method");
        return new ResponseEntity<>(persons, HttpStatus.FOUND);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<PersonResponseDto> findOne(@PathVariable String id) {
        logger.info("Entering findOne method");
        personService.findOne(id);
        logger.info("Exiting findOne method");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
