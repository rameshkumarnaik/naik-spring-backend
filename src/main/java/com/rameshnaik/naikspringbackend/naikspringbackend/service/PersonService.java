package com.rameshnaik.naikspringbackend.naikspringbackend.service;

import com.rameshnaik.naikspringbackend.naikspringbackend.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service class for managing Person entities.
 * This is a simple in-memory implementation for demonstration purposes.
 */
@Service
public class PersonService {
    
    private final Map<Long, Person> persons = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();
    
    // Initialize with some dummy data
    public PersonService() {
        createPerson(new Person(null, "John", "Doe", 30, "john.doe@example.com"));
        createPerson(new Person(null, "Jane", "Smith", 25, "jane.smith@example.com"));
        createPerson(new Person(null, "Bob", "Johnson", 40, "bob.johnson@example.com"));
    }
    
    /**
     * Get all persons.
     * 
     * @return List of all persons
     */
    public List<Person> getAllPersons() {
        return new ArrayList<>(persons.values());
    }
    
    /**
     * Get a person by ID.
     * 
     * @param id The ID of the person to retrieve
     * @return The person with the specified ID, or null if not found
     */
    public Person getPersonById(Long id) {
        return persons.get(id);
    }
    
    /**
     * Create a new person.
     * 
     * @param person The person to create (ID will be auto-generated)
     * @return The created person with the generated ID
     */
    public Person createPerson(Person person) {
        Long id = idCounter.incrementAndGet();
        person.setId(id);
        persons.put(id, person);
        return person;
    }
    
    /**
     * Update an existing person.
     * 
     * @param id The ID of the person to update
     * @param personDetails The updated person details
     * @return The updated person, or null if the person with the specified ID was not found
     */
    public Person updatePerson(Long id, Person personDetails) {
        if (!persons.containsKey(id)) {
            return null;
        }
        
        Person person = persons.get(id);
        person.setFirstName(personDetails.getFirstName());
        person.setLastName(personDetails.getLastName());
        person.setAge(personDetails.getAge());
        person.setEmail(personDetails.getEmail());
        
        persons.put(id, person);
        return person;
    }
    
    /**
     * Delete a person by ID.
     * 
     * @param id The ID of the person to delete
     * @return true if the person was deleted, false if the person with the specified ID was not found
     */
    public boolean deletePerson(Long id) {
        if (!persons.containsKey(id)) {
            return false;
        }
        
        persons.remove(id);
        return true;
    }
}