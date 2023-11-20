/*
package com.example.UserService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class Myconfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                     auth.antMatchers("/user/**").permitAll();
                     auth.antMatchers("/login-user/**").permitAll();
                    auth.antMatchers("/register/**").permitAll();
                     auth.anyRequest().authenticated();
                })
                .oauth2Login(withDefaults())//oath->oath.loginProcessingUrl("/login").loginPage("/user")
                .formLogin(formLogin -> formLogin
                        .loginPage("/loginDV")
                        .loginProcessingUrl("/loginDV"))//oauth2Login -> oauth2Login.loginPage("/login").permitAll()
                .build();
    }
}
*/
