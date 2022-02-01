package com.medicalrecord.medicalrecord.config;

import com.medicalrecord.medicalrecord.filter.CustomAuthenticationFilter;
import com.medicalrecord.medicalrecord.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeHttpRequests().antMatchers("/login", "/api/token/refresh").permitAll();

        http.authorizeHttpRequests().antMatchers("/medicalRecords/**")
                .hasAnyAuthority("ROLE_DOCTOR","ROLE_ADMIN");
        http.authorizeHttpRequests().antMatchers(POST,"/medicalRecords/consultations/**")
               .hasAnyAuthority("ROLE_DOCTOR","ROLE_ADMIN");
        http.authorizeHttpRequests().antMatchers(DELETE,"/medicalRecords/consultations/**")
                .hasAnyAuthority("ROLE_DOCTOR");

//        http.authorizeHttpRequests().antMatchers(GET,"/medicalRecords/prescriptions")
//                .hasAnyAuthority("ROLE_PATIENT");
//        http.authorizeHttpRequests().antMatchers(POST,"/medicalRecords/**")
//                .hasAnyAuthority("ROLE_DOCTOR","ROLE_ADMIN");
//        http.authorizeHttpRequests().antMatchers(POST,"/medicalRecords/consultations")
//                .hasAnyAuthority("ROLE_DOCTOR");
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFilter (authenticationManagerBean()));
        http.addFilterBefore( new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return  super.authenticationManagerBean();
    }

    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("{noop}123").roles("USER","ADMIN").and()
//                .withUser("user").password("{noop}bla").roles("USER");
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests().antMatchers("//**").hasRole("USER").and()
//                .formLogin().and()
//                .logout();
//    }
}
