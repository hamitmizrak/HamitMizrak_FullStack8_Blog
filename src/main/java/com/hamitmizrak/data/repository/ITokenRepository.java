package com.hamitmizrak.data.repository;

import com.hamitmizrak.data.entity.TokenConfirmationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITokenRepository extends CrudRepository<TokenConfirmationEntity,Long> {

    // Delivered Query
   Optional<TokenConfirmationEntity>  findTokenConfirmationEntityByToken(String token);
}
