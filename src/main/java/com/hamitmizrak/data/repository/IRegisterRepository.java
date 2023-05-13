package com.hamitmizrak.data.repository;

import com.hamitmizrak.data.entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRegisterRepository extends JpaRepository<RegisterEntity,Long> {

    //Delivered Query
    //Optional<AdminEntity> findByEmail(String email); // database email bulmak için
    RegisterEntity findByEmail(String email); // database email bulmak için

    // JPQL
    // @Query("SELECT u FROM AdminEntity u WHERE u.status = 1")
    // Collection<AdminEntity> findAllActiveAdminJPQL();

    // Native
    // @Query( value = "SELECT * FROM AdminEntity u WHERE u.status = 1", nativeQuery = true)
    // Collection<AdminEntity> findAllActiveAdminNative();
}
