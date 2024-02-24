package com.evertec.springboot2.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.evertec.springboot2.crud.jwt.JwtTokenUtil;
import com.evertec.springboot2.crud.model.JwtRequest;
import com.evertec.springboot2.crud.model.JwtResponse;
import com.evertec.springboot2.crud.service.UsuarioJWT;

import io.swagger.annotations.ApiOperation;

import com.evertec.springboot2.crud.model.ErrorResponse;

import java.util.logging.Logger;

@RestController
public class AuthenticationController {

    private static final Logger logger = Logger.getLogger(AuthenticationController.class.getName());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UsuarioJWT usuarioJWTService;

    @ApiOperation(value = "Autenticar usuario y generar token JWT")
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        try {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        logger.info("Usuario validado: " + authentication.getName());

        final UserDetails userDetails = usuarioJWTService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    } catch (UsernameNotFoundException | BadCredentialsException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Unauthorized", "Usuario o contraseña incorrectos.", HttpStatus.UNAUTHORIZED.value());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    } catch (Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", "Error interno del servidor.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
    }
}
