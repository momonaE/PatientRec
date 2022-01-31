package com.Record.PatientRecord.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
// import javax.validation.constraints.Future;
// import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Physician  {
  @Id
  @Column(name = "id", nullable = false)
  private Long id;
  @JsonIgnoreProperties
  private String doctorSpecialist;
  @JsonIgnoreProperties

  private LocalDate availableDate;

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
