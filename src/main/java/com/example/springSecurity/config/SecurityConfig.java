package com.example.springSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
//                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/public/**").permitAll() // Permitir acceso sin autenticación
                        //.requestMatchers("/admin/**").hasRole("ADMIN") // Restringir acceso a usuarios con rol ADMIN
                        .requestMatchers("/login").permitAll() // Permitir acceso a la página de login
                        .requestMatchers("/v1/index2").permitAll() // Permitir acceso sin autenticación
                        .anyRequest().authenticated() // Requerir autenticación en todas las demás rutas
                )

                .httpBasic(Customizer.withDefaults())// Habilitar autenticación básica, las contraseñas se manda por cabecera http


        //.formLogin(Customizer.withDefaults()) // Habilitar inicio de sesión con formulario
                .formLogin(form -> form
                     //   .loginPage("/login") // Página personalizada de login
                        .defaultSuccessUrl("/v1/index", true) // Redirige a "/index" después del login exitoso
                        .failureUrl("/login?error=true") // Redirige a "/login" si hay un error
                        .permitAll() // Permitir acceso sin autenticación al formulario de login
                )
                .logout(logout -> logout.logoutSuccessUrl("/login")) // Configurar logout
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Define la política de sesión
                        .sessionFixation(sessionFixation -> sessionFixation
//                                .newSession() // Crea una nueva sesión después de la autenticación, sin copiar atributos
                                        .migrateSession()//Si quieres mantener los datos de sesión pero cambiar el identificador (JSESSIONID)
                        )
                        .maximumSessions(1) // Permite solo una sesión activa por usuario
                        .expiredUrl("/login?expired=true") // Redirigir cuando la sesión expire
                        .sessionRegistry(sessionRegistry()) // Usa el SessionRegistry definido

                );

        return httpSecurity.build();

    }

    @Bean
    public SessionRegistry sessionRegistry() {
        //PARA PODER VER EL FUNCIONAMIENTO SE CREÓ UN METODO DENTRO DE CONTROLLER ( getDetailsSession() )
        // SessionRegistryImpl es la implementación por defecto de SessionRegistry en Spring Security.
        // Se usa para gestionar sesiones activas y controlar la concurrencia de usuarios autenticados.
        //Se usa principalmente en aplicaciones que necesitan gestionar múltiples sesiones o restringir sesiones concurrentes.

        return new SessionRegistryImpl();
    }
}
