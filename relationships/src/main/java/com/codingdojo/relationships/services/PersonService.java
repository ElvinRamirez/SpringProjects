package com.codingdojo.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.relationships.models.Person;
import com.codingdojo.relationships.repositories.PersonRepository;



@Service
public class PersonService {

	private final PersonRepository personRepo;
	
	public PersonService(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }
	
	 // returns all the trips
    public List<Person> allTrips() {
        return personRepo.findAll();
    }
    // creates a trip
    public Person createTrip(Person p) {
        return personRepo.save(p);
    }
    // retrieves a trip
    public Person findById(Long id) {
        Optional<Person> optionalPerson = personRepo.findById(id);
        if(optionalPerson.isPresent()) {
            return optionalPerson.get();
        } else {
            return null;
        }
    }
    
    public Person updateTrip(Person p) {
        return personRepo.save(p);
    }
    
    public void destroy(Long id) {
    	personRepo.deleteById(id);
    	}


}
