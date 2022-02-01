package com.Record.PatientRecord.entity;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Admin  {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
  
  
    private String firstName;
  
    private String lastName;
  
    private Date dob;
  
    private String email;
  
    private String username;
  
    private String password;
  
    private Integer phoneNumber;
  
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address addresses;
  
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Role> roles = new ArrayList<>();
    
}
