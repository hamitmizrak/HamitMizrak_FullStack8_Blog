package com.hamitmizrak.data.repository;

import com.hamitmizrak.data.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IBlogRepository extends JpaRepository<BlogEntity,Long> {

    // Delivered Query
    // Optional<AdminEntity> findByEmail(String email); // database email bulmak için
    BlogEntity findByHeader(String header); // database email bulmak için

    // @Query
    @Query("select t from BlogEntity t")
    List<BlogEntity> mySpecialBlogList();

    // JPQL
    // @Query("SELECT u FROM AdminEntity u WHERE u.status = 1")
    // Collection<AdminEntity> findAllActiveAdminJPQL();

    // Native
    // @Query( value = "SELECT * FROM AdminEntity u WHERE u.status = 1", nativeQuery = true)
    // Collection<AdminEntity> findAllActiveAdminNative();

    //  @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name LIKE %?1%")
    //  public Page<BlogEntity> findAll(String keyword, Pageable pageable);

    // @Query("select t from BlogEntity t join Roles u where u.roleName = :roleName")
    // List<UserEntity> mySpecialBlogList(@Param("roleName") String roleName);
}
