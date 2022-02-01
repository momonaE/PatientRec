package com.medicalrecord.medicalrecord;

import com.medicalrecord.medicalrecord.entity.MedicalRecordUser;
import com.medicalrecord.medicalrecord.entity.Role;
import com.medicalrecord.medicalrecord.services.MedicalRecordUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class MedicalRecordApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalRecordApplication.class, args);
		}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(MedicalRecordUserService medicalRecordUserService){
		return args -> {
			medicalRecordUserService.saveRole(new Role(null, "ROLE_ADMIN"));
			medicalRecordUserService.saveRole(new Role(null, "ROLE_DOCTOR"));
			medicalRecordUserService.saveRole(new Role(null, "ROLE_RECEPTION"));
			medicalRecordUserService.saveRole(new Role(null, "ROLE_PATIENT"));

			medicalRecordUserService.saveUser(new MedicalRecordUser(null, "Hasen", "Hasen", "123", new ArrayList<>()));
			medicalRecordUserService.saveUser(new MedicalRecordUser(null, "Joe", "Joe", "1234", new ArrayList<>()));
			medicalRecordUserService.saveUser(new MedicalRecordUser(null, "Hemen", "Hemen", "123", new ArrayList<>()));

			medicalRecordUserService.addRoleToUser("Hemen", "ROLE_DOCTOR");
			medicalRecordUserService.addRoleToUser("Hemen", "ROLE_ADMIN");
			medicalRecordUserService.addRoleToUser("Joe", "ROLE_RECEPTION");
			medicalRecordUserService.addRoleToUser("Joe", "ROLE_ADMIN");
			medicalRecordUserService.addRoleToUser("Hasen", "ROLE_PATIENT");

		};
	}
}
