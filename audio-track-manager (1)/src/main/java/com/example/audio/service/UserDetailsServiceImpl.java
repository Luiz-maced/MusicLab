package com.example.audio.service;

import com.example.audio.model.AppUser;
import com.example.audio.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.sql.RowSet;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser AppUser;
        AppUser = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        RowSet user = null;
        return User
                .withUsername(user.getUsername())
                .password(user.getPassword()) // senha em formato BCrypt
                .roles(AppUser.getRole())         // por exemplo: "ADMIN" ou "USER"
                .build();
    }
}
