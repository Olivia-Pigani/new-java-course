package com.m2ibank.security;

import com.m2ibank.config.JwtAuthentificationEntryPoint;
import com.m2ibank.config.JwtRequestFilter;
import com.m2ibank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;
    private final JwtAuthentificationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(UserService userService, JwtAuthentificationEntryPoint jwtAuthenticationEntryPoint) {
        this.userService = userService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authz -> authz
                        // Permettre à tous les utilisateurs d'accéder aux endpoints commençant par "/api/auth/**" sans authentification.
                        .requestMatchers("/api/auth/**").permitAll()

                        // Exiger l'authentification pour toutes les autres requêtes qui n'ont pas été matchées par les règles ci-dessus.
                        .anyRequest().authenticated())

                // Configurer le traitement des exceptions pour utiliser le point d'entrée d'authentification JWT personnalisé.
                // Cela est utilisé pour envoyer une réponse d'erreur appropriée si l'authentification échoue.
                .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(jwtAuthenticationEntryPoint))

                // Ajouter un filtre personnalisé (JwtRequestFilter dans cet exemple) avant le filtre d'authentification par nom d'utilisateur et mot de passe.
                // Ce filtre vérifie la présence d'un JWT valide dans les requêtes et l'utilise pour l'authentification.
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

// Construire et retourner la chaîne de filtres de sécurité configurée.
        return http.build();

    }

    // Injection de la configuration d'authentification.
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    // Bean pour obtenir le gestionnaire d'authentification.
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        // Obtention du gestionnaire d'authentification à partir de la configuration.
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Bean pour le filtre d'authentification JWT.
    @Bean
    public JwtRequestFilter jwtAuthenticationFilter() {
        // Création d'un nouveau filtre avec le service utilisateur.
        return new JwtRequestFilter(userService);
    }

    // Bean pour le chiffrement des mots de passe.
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Utilisation de BCrypt pour le chiffrement des mots de passe.
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Collections.singletonList("*")); // Permettre toutes les origines
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")); // Permettre toutes les méthodes
        configuration.setAllowedHeaders(Arrays.asList("*")); // Permettre tous les headers
        configuration.setAllowCredentials(true); // Important pour les cookies, l'autorisation, etc.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Appliquer cette configuration à tous les chemins
        return source;
    }
















}
