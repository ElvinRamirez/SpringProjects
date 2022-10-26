package com.codingdojo.safetravels.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.codingdojo.safetravels.models.Travel;
import com.codingdojo.safetravels.repositories.TravelRepository;



@Service
public class TravelService {
	
	private final TravelRepository travelRepository;
	
	public TravelService(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }
	
	 // returns all the trips
    public List<Travel> allTrips() {
        return travelRepository.findAll();
    }
    // creates a trip
    public Travel createTrip(Travel t) {
        return travelRepository.save(t);
    }
    // retrieves a trip
    public Travel findTrip(Long id) {
        Optional<Travel> optionalTravel = travelRepository.findById(id);
        if(optionalTravel.isPresent()) {
            return optionalTravel.get();
        } else {
            return null;
        }
    }
    
    public Travel updateTrip(Travel t) {
        return travelRepository.save(t);
    }
    
    public void destroy(Long id) {
    		travelRepository.deleteById(id);
    	}
 }
	

