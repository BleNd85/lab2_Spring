package org.example.lab2.Service;

import org.example.lab2.Model.UserEntity;

import java.util.Optional;

public interface UserService {
    Iterable<UserEntity> findAll();

    UserEntity save(UserEntity user);

    Optional<UserEntity> findById(Integer id);

    String deleteById(Integer id);

}
