package com.Record.PatientRecord.service.UtilHelper;

import com.Record.PatientRecord.entity.Address;
import com.Record.PatientRecord.entity.Admin;
import com.Record.PatientRecord.entity.User;

public class AdminHelper {
    public static Admin build(User user) {
        Admin Admin = new Admin();
        Address addresses = user.getAddresses();
        Address address = new Address();
        address.setStreetAddress(addresses.getStreetAddress());
        address.setCity(addresses.getCity());
        address.setState(addresses.getState());
        address.setZipCode(addresses.getZipCode());
        Admin.setAddresses(address);
        Admin.setFirstName(user.getFirstName());
        Admin.setLastName(user.getLastName());
        Admin.setDob(user.getDob());
        Admin.setEmail(user.getEmail());
        Admin.setUsername(user.getUsername());
        Admin.setPassword(user.getPassword());
        Admin.setRoles(user.getRoles());
        Admin.setId(user.getId());
        return Admin;
    }
}
