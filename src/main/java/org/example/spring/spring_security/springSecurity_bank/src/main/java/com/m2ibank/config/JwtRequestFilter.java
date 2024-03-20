package com.m2ibank.config;

import com.m2ibank.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenGenerator;

    private final UserService userService;

    @Autowired
    JwtAuthentificationEntryPoint jwtAuthenticationEntryPoint;

    public JwtRequestFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {

            String token = getJWTFromRequest(request);

            if (token != null && tokenGenerator.validateToken(token)) {
                String username = tokenGenerator.getUsernameFromToken(token);
                UserDetails userDetails = userService.loadUserByUsername(username);

                // Créer un token d'authentification avec les détails de l'utilisateur
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                // Associer des détails supplémentaires au token d'authentification
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Définir le token d'authentification dans le contexte de sécurité
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            // Continuer la chaîne de filtres
            filterChain.doFilter(request, response);
        } catch (AuthenticationException e) {
            // Gérer les exceptions d'authentification
            jwtAuthenticationEntryPoint.commence(request, response, e);

            // Point d'insertion pour le logging des erreurs d'authentification
            // Vous pouvez ajouter un log ici pour tracer l'erreur
        }
    }


    private String getJWTFromRequest(HttpServletRequest request) {
        // Extrait le token JWT de l'en-tête "Authorization" de la requête
        String bearerToken = request.getHeader("Authorization");
        // Vérifie si le token est non null et commence bien par "Bearer "
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            // Extraire le token JWT sans le préfixe "Bearer "
            return bearerToken.substring(7);
        }
        return null; // Retourner null si le token n'est pas présent ou mal formaté
    }
}
