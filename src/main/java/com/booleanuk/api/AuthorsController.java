package com.booleanuk.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("authors")
public class AuthorsController {
    private ArrayList<Author> authors;

    public AuthorsController() {
        this.authors = new ArrayList<>();

        this.authors.add(new Author("JRR Tolkien", "jrr@tolkien.com"));
        this.authors.add(new Author("Jane Austen", "jane@austen.com"));
    }

    @GetMapping
    public ArrayList<Author> getAll() {
        return this.authors;
    }
}