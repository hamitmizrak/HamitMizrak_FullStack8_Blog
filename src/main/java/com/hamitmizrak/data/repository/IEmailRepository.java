package com.hamitmizrak.data.repository;

import com.hamitmizrak.data.entity.EmailSendEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmailRepository extends CrudRepository<EmailSendEntity, Long> {

    // Delivered Query
    Optional<EmailSendEntity> findByEmailTo(String to);
}
