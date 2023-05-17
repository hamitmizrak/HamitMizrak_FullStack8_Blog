package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.user.CustomUserDetail;
import com.hamitmizrak.data.entity.UserEntity;
import com.hamitmizrak.data.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.text.MessageFormat;
import java.util.Optional;

// LOMBOK
@RequiredArgsConstructor

// SERVICE
@Service
public class CustomUserDetailsService implements UserDetailsService {

    // INJECTION
    private final IUserRepository iUserRepository;

    // ## UserDetailsService ############################################################
    // 1.YOL
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> emailData = iUserRepository.findByEmail(email);
        if (emailData.isPresent()) { // Böyle bir kullanıcı var mı
            return  new CustomUserDetail(emailData.get());
        } else
            throw new UsernameNotFoundException(MessageFormat.format("User44 with email data {0} cannot be found ", email));
    }

    //2.YOL
   /*
   @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return iUserRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(email + " nolu ID yoktur"));
    }
    */
}
