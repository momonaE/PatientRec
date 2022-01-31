package com.Record.PatientRecord.service.UtilHelper;

import com.Record.PatientRecord.entity.Address;
import com.Record.PatientRecord.entity.Physician;
import com.Record.PatientRecord.entity.User;

public class PhysicianHelper {
    public static Physician build(User user) {

        Physician Physician = new Physician();
        Address addresses = user.getAddresses();
        Address address = new Address();
        address.setStreetAddress(addresses.getStreetAddress());
        address.setCity(addresses.getCity());
        address.setState(addresses.getState());
        address.setZipCode(addresses.getZipCode());
        Physician.setAddresses(address);
        Physician.setFirstName(user.getFirstName());
        Physician.setLastName(user.getLastName());
        Physician.setDob(user.getDob());
        Physician.setEmail(user.getEmail());
        Physician.setUsername(user.getUsername());
        Physician.setPassword(user.getPassword());
        Physician.setRoles(user.getRoles());
        Physician.setId(user.getId());
        return Physician;
    }

}
