package com.in28minutes.springboot.basics.springbootin10steps;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return Arrays.asList(new Book(1, "The Sound and the Fury", "William Faulkner"));
    }
}