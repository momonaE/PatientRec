package com.Record.PatientRecord;


import com.Record.PatientRecord.service.UserServiceImp;

import java.util.ArrayList;

import com.Record.PatientRecord.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PatientRecordApplication {
	public static void main(String[] args) {
		SpringApplication.run(PatientRecordApplication.class, args);
	}
	

	@Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}

