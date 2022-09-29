package com.twitterapi.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.twitterapi.persistence.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    Optional<User> findById(Long id);

    void deleteById(Long id);
}
