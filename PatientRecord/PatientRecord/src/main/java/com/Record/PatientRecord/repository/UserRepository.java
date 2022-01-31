package com.Record.PatientRecord.repository;

import com.Record.PatientRecord.entity.Role;
import com.Record.PatientRecord.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface UserRepository extends JpaRepository<User,Long> {
    User  findByUsername(String username);  
   // @Query("Select P from Physician P order by  P.availableDate")
}
