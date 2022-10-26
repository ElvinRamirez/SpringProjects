package com.codingdojo.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.relationships.models.License;
import com.codingdojo.relationships.repositories.LicenseRepository;


@Service
public class LicenseService {
	
	private final LicenseRepository licenseRepo;
	
	public LicenseService(LicenseRepository licenseRepo) {
        this.licenseRepo= licenseRepo;
    }
	
	 // returns all the trips
    public List<License> allLicenses() {
        return licenseRepo.findAll();
    }
    // creates a trip
    public License createLicense(License l) {
        return licenseRepo.save(l);
    }
    // retrieves a trip
    public License findById(Long id) {
        Optional<License> optionalLicense = licenseRepo.findById(id);
        if(optionalLicense.isPresent()) {
            return optionalLicense.get();
        } else {
            return null;
        }
    }
    
    public License updateLicense(License l) {
        return licenseRepo.save(l);
    }
    
    public void destroy(Long id) {
    	licenseRepo.deleteById(id);
    	}

}
