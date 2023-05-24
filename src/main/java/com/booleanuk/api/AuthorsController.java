package com.booleanuk.api;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ArrayList<Author>> getAll() {
        if (this.authors.size() > 0){
            return new ResponseEntity<>(this.authors, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getOne(@PathVariable(name = "id") int id) {
        if (id < this.authors.size()) {
            return new ResponseEntity<>(this.authors.get(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
    public ResponseEntity<Author> delete(@PathVariable (name = "id") int id) {
        if (id < this.authors.size()) {
            return new ResponseEntity<>(this.authors.remove(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}