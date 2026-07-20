package com.example.apirest.controller;

import com.example.apirest.dto.AuthRequestDTO;
import com.example.apirest.dto.AuthResponseDTO;
import com.example.apirest.entity.Usuario;
import com.example.apirest.repository.UsuarioRepository;
import com.example.apirest.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // registrar usuario post /api/auth/register
    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody AuthRequestDTO request) {
        // validacion
        if (usuarioRepository.findByUsername(request.getUsername()).isPresent()) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error: El usuario ya existe");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        // crear user y encriptar password
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(request.getUsername());
        nuevoUsuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuarioRepository.save(nuevoUsuario);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Usuario registrado exitosamente");
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    // iniciar sesion post /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@Valid @RequestBody AuthRequestDTO request) {
        try {
            // spring security para autenticar
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Credenciales incorrectas");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }

        // genera token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        // retornar token en respuesta
        return ResponseEntity.ok(new AuthResponseDTO(jwt));
    }
}