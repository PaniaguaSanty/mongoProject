package com.mongoExample.mongo.repository;

import com.mongoExample.mongo.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, String> {

    public List<Person> findByName(String name);

}
