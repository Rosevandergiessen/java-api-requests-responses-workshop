package com.booleanuk.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("authors")
public class AuthorsController {
    private ArrayList<Author> authors;

    public AuthorsController() {
        this.authors = new ArrayList<>();
    }

    @GetMapping
    public ArrayList<Author> getAll() {
        return this.authors;
    }

    @GetMapping("/{id}")
    public Author getOne(@PathVariable(name = "id") int id) {
        if (id < this.authors.size()) {
            return this.authors.get(id);
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author create(@RequestBody Author author) {
        this.authors.add(author);
        author.setId(this.authors.indexOf(author));
        return author;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Author update(@PathVariable (name = "id") int id, @RequestBody Author author) {
        if (id < this.authors.size()) {
            this.authors.get(id).setName(author.getName());
            this.authors.get(id).setEmail(author.getEmail());
            return this.authors.get(id);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Author delete(@PathVariable (name = "id") int id) {
        if (id < this.authors.size()) {
            return this.authors.remove(id);
        }
        return null;
    }

}