package com.Record.PatientRecord.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Data

public class Patient  {
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
