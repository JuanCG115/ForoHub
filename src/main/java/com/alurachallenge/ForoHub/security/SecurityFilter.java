package com.alurachallenge.ForoHub.security;

import com.alurachallenge.ForoHub.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var path = request.getRequestURI();
        if (path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs") || path.startsWith("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 1. Extraer el header
        var authHeader = request.getHeader("Authorization");

        // 2. Solo si el header existe y empieza con Bearer, intentamos validar
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            var token = authHeader.replace("Bearer ", "");
            try {
                var nombreUsuario = tokenService.getSubject(token);
                if (nombreUsuario != null) {
                    var usuario = usuarioRepository.findByCorreoElectronico(nombreUsuario);
                    var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (RuntimeException e) {
                // Si el token es inválido, no autenticamos, pero dejamos que la cadena siga
                // (o podrías manejar un error específico aquí)
            }
        }

        // 3. ¡IMPORTANTE! Esta línea siempre debe ejecutarse
        filterChain.doFilter(request, response);
    }
}
