package com.codingdojo.dojosandninjas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.dojosandninjas.models.Ninja;
import com.codingdojo.dojosandninjas.repositories.NinjaRepository;


@Service
public class NinjaService {
	private final NinjaRepository ninjaRepo;
	
	public NinjaService(NinjaRepository ninjaRepo) {
		this.ninjaRepo = ninjaRepo;
	}
	
	 // returns all Ninjas
    public List<Ninja> allNinjas() {
        return ninjaRepo.findAll();
    }
    // creates a Ninja	
    public Ninja createNinja(Ninja n) {
        return ninjaRepo.save(n);
    }
    
    // retrieves a Ninjas
    public Ninja findById(Long id) {
        Optional<Ninja> optionalNinja  = ninjaRepo.findById(id);
        if(optionalNinja.isPresent()) {
            return optionalNinja.get();
        } else {
            return null;
        }
    }
    
    //Update
    public Ninja updateNinja(Ninja d) {
        return ninjaRepo.save(d);
    }
    
    //Delete
    public void destroy(Long id) {
    	ninjaRepo.deleteById(id);
    	}
}
