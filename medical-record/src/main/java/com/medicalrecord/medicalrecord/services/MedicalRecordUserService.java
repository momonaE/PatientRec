package com.medicalrecord.medicalrecord.services;

import com.medicalrecord.medicalrecord.entity.MedicalRecordUser;
import com.medicalrecord.medicalrecord.entity.Role;
import com.medicalrecord.medicalrecord.repositories.MedicalRecordUserRepository;
import com.medicalrecord.medicalrecord.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class MedicalRecordUserService implements MedicalRecordUserIService, UserDetailsService {
    @Resource
    private final MedicalRecordUserRepository medicalRecordUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MedicalRecordUser user = medicalRecordUserRepository.findByName(username);
        if(username == null){
        log.error("User name not found in the database.");
        throw new UsernameNotFoundException("User name not found in the database");
        }
        else
            log.info("User name found in the database {}", username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);

    }
    @Override
    public MedicalRecordUser saveUser(MedicalRecordUser user) {
        log.info("Saving new user{} to the database", user.getUserName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return medicalRecordUserRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role{} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("Adding role{} to user{}",roleName , userName);
    MedicalRecordUser user = medicalRecordUserRepository.findByName(userName);
    Role role = roleRepository.findByName(roleName);
    // To do validation
    user.getRoles().add(role);
    }

    @Override
    public MedicalRecordUser getUser(String userName) {
        System.out.println("Service" +userName);
        log.info("Getting user {}", userName);
        return medicalRecordUserRepository.findByName(userName);
    }

    @Override
    public List<MedicalRecordUser> getUsers() {
        log.info("Fetching all users");
        return medicalRecordUserRepository.findAll();
    }


}
