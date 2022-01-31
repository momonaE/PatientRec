package com.Record.PatientRecord.controller;

import com.Record.PatientRecord.entity.*;
import com.Record.PatientRecord.service.UserServiceImp;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.util.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(value = "/users")
@Slf4j
public class UserController {
    private static final String APPLICATION_JSON_VALUE = "application/json";

    @Autowired
    private  UserServiceImp userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }
   @PostMapping("/save")
   public ResponseEntity<?> saveUser(@Valid @RequestBody User user,HttpServletRequest request,HttpServletResponse response) throws StreamWriteException, DatabindException, IOException {

       System.out.println("from controller");
       
       URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/save").toUriString());
        if(userService.saveUser(user)==null){
            log.error("Error logggin :","user exist");
            response.setHeader("Error","user exist");
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.created(uri).body(userService.saveUser(user));

        }
   }
   @PostMapping("/role/save")
   public ResponseEntity<Role> saveRole(@RequestBody Role role) {
    URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/role/save").toUriString());

       return ResponseEntity.created(uri).body(userService.saveRole(role));
   }
   @PostMapping("/role/addtouser")
   public ResponseEntity<?> addRoleToUser(@RequestBody RoleTouserForm form) {
    userService.addRoleToUser(form.getUsername(),form.getRolename());
       return ResponseEntity.ok().build();
   }
   @GetMapping("/refreshToken/")
   public void refershToken(HttpServletRequest request,HttpServletResponse response) throws StreamWriteException, DatabindException, IOException {
    String authorizationHeader =request.getHeader(AUTHORIZATION);
    if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")){
        try{
           String referesh_token = authorizationHeader.substring("Bearer ".length());
           Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
           JWTVerifier verifier = JWT.require(algorithm).build();
           DecodedJWT decodedJWT =verifier.verify(referesh_token);
           String username = decodedJWT.getSubject();
           User user =userService.getUser(username);
           String access_token = JWT.create()
           .withSubject(user.getUsername())
           .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 100))
           .withIssuer(request.getRequestURI().toString())
           .withClaim("roles", user.getRoles().stream().map(Role::getName)
                   .collect(Collectors.toList()))
           .sign(algorithm);
           Map<String, String> tokens = new HashMap<>();
        tokens.put("access+token", access_token);
        tokens.put("referesh_token", referesh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
           

        } catch(Exception exception){
           log.error("Error logggin :"+exception.getMessage());
           response.setHeader("Error", exception.getMessage());
           Map<String,String> error = new HashMap<>();
           error.put("error message",exception.getMessage());
           response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
           new ObjectMapper().writeValue(response.getOutputStream(), error);
        }   
        }else{
            throw  new RuntimeException("Referesh token is missing ");
        }

   }

   


}
@Data
class RoleTouserForm{
    private String username;
    private String rolename;
}
