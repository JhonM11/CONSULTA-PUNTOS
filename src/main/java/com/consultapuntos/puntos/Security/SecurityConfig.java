package com.consultapuntos.puntos.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration

public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/puntos/auth/**").permitAll()
                        //Autorizaciones para el usuarios con ROL ADMIN

                        .requestMatchers("/api/v1/puntos/users/create").hasRole("ADMIN")
                        .requestMatchers("/api/v1/puntos/users/inactivate/").hasRole("ADMIN")
                        .requestMatchers("/api/v1/puntos/users/activate/").hasRole("ADMIN")
                        .requestMatchers("/api/v1/puntos/users/reset-password/").hasRole("ADMIN")

                        .requestMatchers("/api/v1/puntos/tipo-conexiones/findTypeConnectionBycode/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/puntos/tipo-conexiones/getAllConnection").hasRole("ADMIN")
                        .requestMatchers("/api/v1/puntos/tipo-conexiones/updateTypeConnectionByCode/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/puntos/tipo-conexiones/createTypeConnection").hasRole("ADMIN")


                        //Autorizaciones para usuarios con ROL ADMIN Y AUXILIAR
                        .requestMatchers("/api/v1/puntos/users/change-passwd").hasAnyRole("ADMIN", "AUXILIAR")
                        .requestMatchers("/api/v1/puntos/users/mysession").hasAnyRole("ADMIN", "AUXILIAR")
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, UserDetailsService uds) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(uds)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
