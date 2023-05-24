package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.business.services.ITokenServices;
import com.hamitmizrak.data.entity.TokenConfirmationEntity;
import com.hamitmizrak.data.repository.ITokenRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// LOMBOK
@Data
@RequiredArgsConstructor // Injection

@Transactional
@Service
public class TokenConfirmationServiceImpl implements ITokenServices<TokenConfirmationEntity> {

    // INJECTION
    private final ITokenRepository iTokenRepository;

    // CREATE TOKEN
    // Mailine gelen linke tıkladığımda çalışacak metot
    @Override
    public String createToken(TokenConfirmationEntity tokenConfirmationEntity) {
        TokenConfirmationEntity tokenConfirmationEntity1=iTokenRepository.save(tokenConfirmationEntity);
        return tokenConfirmationEntity1.getToken();
    }

    // DELETE TOKEN
    // Mailine gelen linke tıkladığımda token silinecek metot
    @Override
    public void deleteToken(Long id) {
         iTokenRepository.deleteById(id);
    }
} //end class
