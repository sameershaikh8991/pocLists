package com.auth.repo;

import com.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserrRepo extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);
}
