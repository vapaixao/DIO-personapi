package com.vapaixao.personapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundExeption extends Exception {

    public PersonNotFoundExeption(Long id) {
        super(String.format("Person with ID %s not found ", id));
    }
}
