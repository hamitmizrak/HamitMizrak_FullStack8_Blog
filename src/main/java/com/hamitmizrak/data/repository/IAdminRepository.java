package com.hamitmizrak.data.repository;

import com.hamitmizrak.data.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<AdminEntity,Long> {

    //Delivered Query
    //Optional<AdminEntity> findByEmail(String email); // database email bulmak için
    AdminEntity findByEmail(String email); // database email bulmak için
}
