package com.hamitmizrak.data.repository;

import com.hamitmizrak.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<UserEntity, Long> {

    // Delivered Query
    Optional<UserEntity> findByEmail(String email);
}
