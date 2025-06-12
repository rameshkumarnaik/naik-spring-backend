package com.rameshnaik.naikspringbackend.naikspringbackend.controller;

import com.rameshnaik.naikspringbackend.naikspringbackend.model.Person;
import com.rameshnaik.naikspringbackend.naikspringbackend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing Person resources.
 */
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * GET /api/persons : Get all persons.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of persons in the body
     */
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    /**
     * GET /api/persons/:id : Get the person with the specified ID.
     *
     * @param id the ID of the person to retrieve
     * @return the ResponseEntity with status 200 (OK) and the person in the body,
     *         or with status 404 (Not Found) if the person is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personService.getPersonById(id);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    /**
     * POST /api/persons : Create a new person.
     *
     * @param person the person to create
     * @return the ResponseEntity with status 201 (Created) and the new person in the body
     */
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person createdPerson = personService.createPerson(person);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    /**
     * PUT /api/persons/:id : Update an existing person.
     *
     * @param id the ID of the person to update
     * @param person the person to update
     * @return the ResponseEntity with status 200 (OK) and the updated person in the body,
     *         or with status 404 (Not Found) if the person is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        Person updatedPerson = personService.updatePerson(id, person);
        if (updatedPerson == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    /**
     * DELETE /api/persons/:id : Delete the person with the specified ID.
     *
     * @param id the ID of the person to delete
     * @return the ResponseEntity with status 204 (No Content) if successful,
     *         or with status 404 (Not Found) if the person is not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        boolean deleted = personService.deletePerson(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}