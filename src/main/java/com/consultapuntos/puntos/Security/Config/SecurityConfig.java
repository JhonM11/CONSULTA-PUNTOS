package com.consultapuntos.puntos.Security.Config;

import com.consultapuntos.puntos.Security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.consultapuntos.puntos.Security.Config.ApiRoutes.*;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth

                        // === AUTH (Permitido a todos) ===
                        .requestMatchers(AUTH).permitAll()
                        .requestMatchers(PUNTOS_DOWNLOAD_TEMPLATE).permitAll()


                        // === ADMIN ===
                        .requestMatchers(PUNTOS_CREATE).hasRole("ADMIN")
                        .requestMatchers(PUNTOS_DELETE).hasRole("ADMIN")
                        .requestMatchers(USERS_UPDATE_ROLE).hasRole("ADMIN")

                        // === ADMIN y COORDINADOR ===
                        .requestMatchers(USERS_LIST).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(USERS_CREATE).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(USERS_INACTIVATE).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(USERS_ACTIVATE).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(USERS_RESET_PASSWORD).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(PUNTOS_UPDATE).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(PUNTOS_REPORTS).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(PUNTOS_REPORTS_FORMAT_ANSIBLE).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(PUNTOS_IMPORT_EXCEL).hasAnyRole("ADMIN", "COORDINADOR")

                        .requestMatchers(TIPO_CONEXION_FIND_BY_CODE).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(TIPO_CONEXION_GET_ALL).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(TIPO_CONEXION_UPDATE).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(TIPO_CONEXION_CREATE).hasAnyRole("ADMIN", "COORDINADOR")

                        .requestMatchers(ZONAS_FIND_BY_CODE).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(ZONAS_GET_ALL).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(ZONAS_UPDATE_NAME).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(ZONAS_CREATE).hasAnyRole("ADMIN", "COORDINADOR")

                        .requestMatchers(CCOSTO_FIND_BY_CODE).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(CCOSTO_LIST).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(CCOSTO_UPDATE_NAME).hasAnyRole("ADMIN", "COORDINADOR")
                        .requestMatchers(CCOSTO_CREATE).hasAnyRole("ADMIN", "COORDINADOR")

                        // === ADMIN, COORDINADOR y AUXILIAR ===
                        .requestMatchers(USERS_CHANGE_PASSWORD).hasAnyRole("ADMIN", "COORDINADOR", "AUXILIAR")
                         .requestMatchers(USERS_MY_SESSION).hasAnyRole("ADMIN", "COORDINADOR", "AUXILIAR")

                        .requestMatchers(PUNTOS_LIST).hasAnyRole("ADMIN", "COORDINADOR", "AUXILIAR")
                        .requestMatchers(PUNTOS_FIND_BY_CODIGO).hasAnyRole("ADMIN", "COORDINADOR", "AUXILIAR")
                        .requestMatchers(PUNTOS_FIND_BY_NOMBRE).hasAnyRole("ADMIN", "COORDINADOR", "AUXILIAR")
                        .requestMatchers(PUNTOS_FIND_BY_IP).hasAnyRole("ADMIN", "COORDINADOR", "AUXILIAR")
                        .requestMatchers(PUNTOS_FIND_BY_CODIGO_AS_TEXT).hasAnyRole("ADMIN", "COORDINADOR", "AUXILIAR")

                        .anyRequest().authenticated()
                )
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
