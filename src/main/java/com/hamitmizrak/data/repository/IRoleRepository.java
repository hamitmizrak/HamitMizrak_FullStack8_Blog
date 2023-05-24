package com.hamitmizrak.data.repository;

import com.hamitmizrak.data.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    //Delivered Query
    //Optional<RoleEntity> findByRoleName(String roleName); // database email bulmak için
    //RoleEntity findByRoleName(String roleName); // database email bulmak için

    // JPQL
    // @Query("SELECT u FROM RoleEntity u WHERE u.roleName = 1")
    // Collection<RoleEntity> kendiSorgumuYazdimJPQL();

    // Native
    // @Query( value = "SELECT * FROM RoleEntity u WHERE u.roleName = 1", nativeQuery = true)
    // Collection<RoleEntity> kendiSorgumuYazdimNative();
} //end class
