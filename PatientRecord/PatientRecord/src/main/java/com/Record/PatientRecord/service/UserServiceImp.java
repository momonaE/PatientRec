package com.Record.PatientRecord.service;

import com.Record.PatientRecord.service.UtilHelper.AdminHelper;
import com.Record.PatientRecord.service.UtilHelper.PatientHelper;
import com.Record.PatientRecord.service.UtilHelper.PhysicianHelper;
import com.mysql.cj.log.Log;
import com.Record.PatientRecord.entity.User;
import com.Record.PatientRecord.repository.RoleRepository;
import com.Record.PatientRecord.repository.UserRepository;

import java.util.ArrayList;
import java.util.*;

import javax.transaction.Transactional;

import com.Record.PatientRecord.entity.Patient;
import com.Record.PatientRecord.entity.Role;
import com.Record.PatientRecord.entity.RoleEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements UserInterface, UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImp.class);
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    private PhysicianService physicianService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("User found in database :{}", username);
        User user = userRepo.findByUsername(username);
        if (user == null) {
            log.error("user not found in the database");
        } else {
            log.info("User found in database :{}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            System.out.println("from user"+role.getName());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                authorities);
    }

    @Override
    public User saveUser(User user) {
        User s = userRepo.findByUsername(user.getUsername());
        if (s != null) {
            log.info("user already exxist");
            return null;
        }
        log.info("Saving new user {} to the database ", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRoles().get(0).getName() != null) {

            userRepo.save(user);
            // roleRepo.save("ROLE_PATIENT");
            //saveRole(new Role(null, "ROLE_PATIENT"));
           // addRoleToUser(user.getUsername(), "ROLE_PATIENT");
            User users = userRepo.findByUsername(user.getUsername());
            if (user.getRoles().get(0).getName().equals("PHYSICIAN")) {
                physicianService.addPhysician(PhysicianHelper.build(users));
            }

            if (user.getRoles().get(0).getName().equals("ROLE_PATIENT")) {
                patientService.savePatient(PatientHelper.build(users));
            }
            if (user.getRoles().get(0).getName().equals("ADMIN")) {
                adminService.addAdmin(AdminHelper.build(users));
            }
            return userRepo.save(user);
        } else {
            log.error("user need to select it's role");

            return null;
        }
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database ", role.getName());

        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        log.info("adding   role {} to the database ", username, rolename);

        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(rolename);
        System.out.println("from where" + role.getName());
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("fetching user {}", username);

        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("fetching all users {}");

        return userRepo.findAll();
    }

}
