package com.example.audio.repository;

import com.example.audio.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    default Optional<AppUser> findByUsername(String username) {
        return null;
    }
}
