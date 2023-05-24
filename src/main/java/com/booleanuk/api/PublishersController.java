package com.booleanuk.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("publishers")
public class PublishersController {
    private ArrayList<Publisher> publishers;

    public PublishersController() {
        this.publishers = new ArrayList<>();
    }

    @GetMapping
    public ResponseEntity<ArrayList<Publisher>> getAll() {
        if (this.publishers.size() > 0){
            return new ResponseEntity<>(this.publishers, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getOne(@PathVariable(name = "id") int id) {
        if (id < this.publishers.size()) {
            return new ResponseEntity<>(this.publishers.get(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher create(@RequestBody Publisher publisher) {
        this.publishers.add(publisher);
        publisher.setId(this.publishers.indexOf(publisher) + 1);
        return publisher;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher update(@PathVariable (name = "id") int id, @RequestBody Publisher publisher) {
        if (id < this.publishers.size()) {
            this.publishers.get(id).setName(publisher.getName());
            this.publishers.get(id).setCity(publisher.getCity());
            return this.publishers.get(id);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Publisher> delete(@PathVariable (name = "id") int id) {
        if (id < this.publishers.size()) {
            return new ResponseEntity<>(this.publishers.remove(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
