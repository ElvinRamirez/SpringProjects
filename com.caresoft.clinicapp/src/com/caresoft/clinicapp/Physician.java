package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

public class Physician extends User implements HIPAAcompliantUser {
//... imports class definition...
    public Physician (Integer id){
		super(id);
    }
    
    @Override
    public boolean assignPin(int pin) {
    	if (pin <100 || pin > 9999) {
    		return false;
    	}
    	this.pin = pin;
    	return true;
    }
    
    @Override
    public boolean accessAuthorized(Integer confirmedAuthId) {
    	if (this.id == confirmedAuthId) {
    	return true;
    	}
    	return false;
    }
    // Inside class:    
    private ArrayList<String> patientNotes;
	

    // TO DO: Implement HIPAACompliantUser!
	
    public void newPatientNotes(String notes, String patientName, Date date) {
        String report = String.format(
            "Datetime Submitted: %s \n", date);
        report += String.format("Reported By ID: %s\n", this.id);
        report += String.format("Patient Name: %s\n", patientName);
        report += String.format("Notes: %s \n", notes);
        this.patientNotes.add(report);
    }

	public ArrayList<String> getPatientNotes() {
		return patientNotes;
	}

	public void setPatientNotes(ArrayList<String> patientNotes) {
		this.patientNotes = patientNotes;
	}
	
    // TO DO: Setters & Getters

}
