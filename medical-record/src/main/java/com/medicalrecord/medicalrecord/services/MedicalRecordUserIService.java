package com.medicalrecord.medicalrecord.services;

import com.medicalrecord.medicalrecord.entity.MedicalRecordUser;
import com.medicalrecord.medicalrecord.entity.Role;

import java.util.List;

public interface MedicalRecordUserIService {
    MedicalRecordUser saveUser(MedicalRecordUser medicalRecordUser);
    Role saveRole(Role role);
    void  addRoleToUser(String userName, String roleName);// no duplicate usernames
    MedicalRecordUser getUser(String userName);
    List<MedicalRecordUser> getUsers();


}
