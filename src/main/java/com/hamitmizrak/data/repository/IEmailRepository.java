package com.hamitmizrak.data.repository;

import com.hamitmizrak.data.entity.EmailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IEmailRepository extends CrudRepository<EmailEntity,Long> {

    //Delivered Query
   Optional<EmailEntity>  findByEmailTo(String email);
}
