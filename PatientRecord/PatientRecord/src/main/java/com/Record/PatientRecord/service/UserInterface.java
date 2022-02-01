package com.Record.PatientRecord.service;

import com.Record.PatientRecord.entity.*;
import java.util.List;
public interface UserInterface {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String rolename);
    User getUser(String username);
    List<User> getUsers();
}
