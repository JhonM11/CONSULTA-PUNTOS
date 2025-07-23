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
                        // ****** ENTIDAD PUNTOS *******
                        .requestMatchers("/api/v1/puntos/create").hasAnyRole("ADMIN")
                        .requestMatchers("/api/v1/puntos/delete/**").hasAnyRole("ADMIN")


                        //Autorizaciones para el usuarios con ROL ADMIN y COORDINADOR
                        // ****** ENTIDAD PUNTOS *******
                        .requestMatchers("/api/v1/puntos/users/create").hasAnyRole("ADMIN","COORDINADOR")
                        .requestMatchers("/api/v1/puntos/users/inactivate/").hasAnyRole("ADMIN","COORDINADOR")
                        .requestMatchers("/api/v1/puntos/users/activate/").hasAnyRole("ADMIN","COORDINADOR")
                        .requestMatchers("/api/v1/puntos/users/reset-password/").hasAnyRole("ADMIN","COORDINADOR")
                        .requestMatchers("/api/v1/puntos/update/**").hasAnyRole("ADMIN","COORDINADOR")
                        .requestMatchers("/api/v1/puntos/reports/**").hasAnyRole("ADMIN","COORDINADOR")
                        .requestMatchers("/api/v1/puntos/reports-FormatAnsible/**").hasAnyRole("ADMIN","COORDINADOR")

                        // ****** ENTIDAD TIPO CONEXION *******
                        .requestMatchers("/api/v1/puntos/tipo-conexiones/findTypeConnectionBycode/**").hasAnyRole("ADMIN","COORDINADOR")
                        .requestMatchers("/api/v1/puntos/tipo-conexiones/getAllConnection").hasAnyRole("ADMIN","COORDINADOR")
                        .requestMatchers("/api/v1/puntos/tipo-conexiones/updateTypeConnectionByCode/**").hasAnyRole("ADMIN","COORDINADOR")
                        .requestMatchers("/api/v1/puntos/tipo-conexiones/createTypeConnection").hasAnyRole("ADMIN","COORDINADOR")

                        // ****** ENTIDAD ZONAS *******
                        .requestMatchers("/api/v1/puntos/zonas/findByCode/**").hasAnyRole("ADMIN","COORDINADOR")
                        .requestMatchers("/api/v1/puntos/zonas/getAll").hasAnyRole("ADMIN","COORDINADOR")
                        .requestMatchers("/api/v1/puntos/zonas/updateNameByCode/**").hasAnyRole("ADMIN","COORDINADOR")
                        .requestMatchers("/api/v1/puntos/zonas/create").hasAnyRole("ADMIN","COORDINADOR")

                        // ****** ENTIDAD CENTROS DE COSTO *******
                        .requestMatchers("/api/v1/puntos/centros-costos/findByCode/**").hasAnyRole("ADMIN","COORDINADOR")
                        .requestMatchers("/api/v1/puntos/centros-costos/list").hasAnyRole("ADMIN","COORDINADOR")
                        .requestMatchers("/api/v1/puntos/centros-costos/updateName/**").hasAnyRole("ADMIN","COORDINADOR")
                        .requestMatchers("/api/v1/puntos/centros-costos/create").hasAnyRole("ADMIN","COORDINADOR")


                        //Autorizaciones para usuarios con ROL ADMIN, COORDINADOR y AUXILIAR
                        // ****** ENTIDAD USER *******
                        .requestMatchers("/api/v1/puntos/users/change-passwd").hasAnyRole("ADMIN","COORDINADOR","AUXILIAR")
                        .requestMatchers("/api/v1/puntos/users/mysession").hasAnyRole("ADMIN","COORDINADOR", "AUXILIAR")

                        // ****** ENTIDAD PUNTOS *******
                        .requestMatchers("/api/v1/puntos/list").hasAnyRole("ADMIN","COORDINADOR", "AUXILIAR")
                        .requestMatchers("/api/v1/puntos/findByCodigo/**").hasAnyRole("ADMIN","COORDINADOR", "AUXILIAR")
                        .requestMatchers("/api/v1/puntos/findByNombre/**").hasAnyRole("ADMIN","COORDINADOR", "AUXILIAR")
                        .requestMatchers("/api/v1/puntos/findByIp/**").hasAnyRole("ADMIN","COORDINADOR", "AUXILIAR")
                        .requestMatchers("/api/v1/puntos/findByCodigoAsText/**").hasAnyRole("ADMIN","COORDINADOR", "AUXILIAR")
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
