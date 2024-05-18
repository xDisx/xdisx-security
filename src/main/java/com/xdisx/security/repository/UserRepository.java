package com.xdisx.security.repository;

import com.xdisx.security.repository.db.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, BigInteger> {
    boolean existsByUsername(String username);
    Optional<UserEntity> findByUsername(String username);
}
