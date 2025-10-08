package com.generation.ripassorequestback.model.repositories;

import com.generation.ripassorequestback.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>
{
    Optional<User> findByToken(String token);
    Optional<User> findByUsernameAndPassword(String username, String password);

}
