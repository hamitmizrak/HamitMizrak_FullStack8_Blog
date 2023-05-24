package com.hamitmizrak.data.repository;

import com.hamitmizrak.data.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// NOT: interface için önemli bilgiler
// 1-) interface extends ile başka bir interface ekleyebilirsin. =>
// public interface IProfileHeaderApp extends IModelMapperService

// 2-) interface abstract ekleyerek implements eden class bütün metotları eklemez. =>
// abstract public interface IProfileHeaderApp
public interface IUserRepository extends CrudRepository<UserEntity, Long> {

	//Kendi Sorgumu Yazdım
	//@Query("SELECT u FROM UserEntity u WHERE u.username = :username")
	//public UserEntity getUserByUsername(@Param("username") String username);

	// Delivered Query
	Optional<UserEntity> findByEmail(String email);
	UserEntity findByName (String name);

}
