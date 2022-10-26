package com.caresoft.clinicapp;

public interface HIPAAcompliantUser {
    abstract boolean assignPin(int pin);
    abstract boolean accessAuthorized(Integer confirmedAuthID);
}
