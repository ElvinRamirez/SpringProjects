package com.codingdojo.dojosandninjas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.dojosandninjas.models.Dojo;
import com.codingdojo.dojosandninjas.repositories.DojoRepository;



@Service
public class DojoService {
	
	private final DojoRepository dojoRepo;
	
	public DojoService(DojoRepository dojoRepo) {
		this.dojoRepo = dojoRepo;
	}
	
	 // returns all Dojos
    public List<Dojo> allDojos() {
        return dojoRepo.findAll();
    }
    // creates a Dojo	
    public Dojo createDojo(Dojo d) {
        return dojoRepo.save(d);
    }
    
    // retrieves a Dojo
    public Dojo findById(Long id) {
        Optional<Dojo > optionalDojo  = dojoRepo.findById(id);
        if(optionalDojo.isPresent()) {
            return optionalDojo.get();
        } else {
            return null;
        }
    }
    
    //Update
    public Dojo updateDojo(Dojo d) {
        return dojoRepo.save(d);
    }
    
    //Delete
    public void destroy(Long id) {
    	dojoRepo.deleteById(id);
    	}
}
