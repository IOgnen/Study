package com.library.controller;
import com.library.entity.Publisher;
import com.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PublisherController {

    @Autowired
    PublisherRepository publisherRepository;

    @PostMapping("/publisher")
    public ResponseEntity<Publisher> insertBook(@RequestBody Publisher publisher) {

        try {
            publisherRepository.save(publisher);
            return new ResponseEntity<Publisher>(publisher, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Publisher>(publisher, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/publisher")
    public ResponseEntity<List<Publisher>> getAllPublishers() {

        List<Publisher> result = new ArrayList<Publisher>();

        try {

            result = publisherRepository.findAll();


            if (result.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/publisher/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable("id") int id){

        try {

            int theid = id;

            Publisher result = publisherRepository.getById(id);

            if(result != null) {
                return new ResponseEntity<Publisher>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
