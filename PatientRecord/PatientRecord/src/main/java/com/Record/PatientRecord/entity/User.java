package com.Record.PatientRecord.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;
    @NotNull(message = "lastName cannot be null")
    @NotBlank(message = "lastName cannot be empty")
    @Size(min = 1)
    private String firstName;
    @NotNull(message = "lastName cannot be null")
    @NotBlank(message = "lastName cannot be empty")
    @Size(min = 1)
    private String lastName;
    @Past
    private Date dob;
    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be empty")
    @Email
    private String email;
    @NotNull(message = "username canot be null")
    @NotBlank(message = "username cannot be empty")

    private String username;

    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be empty")
    @Pattern(regexp = "(?=.*?[0-9])(?=.*?[A-Za-z]).+", message = "password must contain at least  one character and number")
    private String password;
    @Positive
    private Integer phoneNumber;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address addresses;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Role> roles = new ArrayList<>();

}
