package com.mongoExample.mongo.service;

import java.util.List;

public interface ICRUD<T> {

    T delete(String dni);

    T findOne(String dni);

    List<T> findAll();
}
