package com.evertec.springboot2.crud.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioJWT implements UserDetailsService {

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String noEncodedPassword;

    private final PasswordEncoder passwordEncoder;

    public UsuarioJWT(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals(this.username)) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        String encodedPassword = passwordEncoder.encode(this.noEncodedPassword); // Codifica la contraseña configurada

        return User.withUsername(username)
                .password(encodedPassword) // Utiliza la contraseña codificada
                .roles("ADMIN")
                .build();
    }
}
