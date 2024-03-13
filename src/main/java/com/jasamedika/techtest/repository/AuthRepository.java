package com.jasamedika.techtest.repository;

import com.jasamedika.techtest.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, String> {

    Optional<Auth> findFirstByToken(String token);
}
