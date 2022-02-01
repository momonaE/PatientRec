package com.Record.PatientRecord.service.UtilHelper;

import com.Record.PatientRecord.entity.Address;
import com.Record.PatientRecord.entity.Patient;
import com.Record.PatientRecord.entity.User;

public class PatientHelper {
    public static Patient build(User user) {

        Patient Patient = new Patient();
        Address addresses = user.getAddresses();
        Address address = new Address();
        address.setStreetAddress(addresses.getStreetAddress());
        address.setCity(addresses.getCity());
        address.setState(addresses.getState());
        address.setZipCode(addresses.getZipCode());
        Patient.setAddresses(address);
        Patient.setFirstName(user.getFirstName());
        Patient.setLastName(user.getLastName());
        Patient.setDob(user.getDob());
        Patient.setEmail(user.getEmail());
        Patient.setUsername(user.getUsername());
        Patient.setPassword(user.getPassword());
          Patient.setRoles(user.getRoles());
          Patient.setId(user.getId());
        return Patient;
    }
    
}
