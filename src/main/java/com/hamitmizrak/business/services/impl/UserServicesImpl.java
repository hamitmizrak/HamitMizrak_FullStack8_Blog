package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.business.dto.EmailDto;
import com.hamitmizrak.business.dto.UserDto;
import com.hamitmizrak.business.services.ITokenServices;
import com.hamitmizrak.business.services.IUserService;
import com.hamitmizrak.data.entity.TokenConfirmationEntity;
import com.hamitmizrak.data.entity.UserEntity;
import com.hamitmizrak.data.repository.ITokenRepository;
import com.hamitmizrak.data.repository.IUserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Optional;

// LOMBOK
@RequiredArgsConstructor // injection

// SERVICE
@Service
@Component //spring'in bir parçası olduğunu teyit ediyorum.
public class UserServicesImpl implements UserDetailsService, IUserService {

    // INJECTION
    private final IUserRepository iUserRepository; // kullanıcı oluşturma
    private final ITokenRepository iTokenRepository; // Token oluşturma
    private final ITokenServices tokenServices;
    private final JavaMailSender mailSender; // Mail oluşturma
    private final PasswordEncoderBean passwordEncoderBean; // şifre maskeleme
    private final ModelMapperBean modelMapperBean; // DTO => ENTITY

    // MODEL MAPPER
    @Override
    public UserEntity DtoToEntity(UserDto userDto) {
        return modelMapperBean.modelMapperMethod().map(userDto, UserEntity.class);
    }

    @Override
    public UserDto EntityToDto(UserEntity userEntity) {
        return modelMapperBean.modelMapperMethod().map(userEntity, UserDto.class);
    }

    // ## UserDetailsService ############################################################
    // 1.YOL
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> myFindUser = iUserRepository.findByEmail(email);
        if (myFindUser.isPresent()) { // Böyle bir kullanıcı var mı
            return myFindUser.get();
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

    // ## IGenericUserService ############################################################
    private String[] allEmailAddress() {
        String myEmailAddres = "denememalatya4444@gmail.com";
        String otherEmailAddress = "hamitmizrak@gmail.com";
        String[] emailArray = {myEmailAddres, otherEmailAddress};
        return emailArray;
    }

    @Override
    public void emailTokenConfirmation(TokenConfirmationEntity tokenConfirmationEntity) {
        // @OneToOne(1-1) ilişkideki veriyi almak
        // TokenConfirmationEntity'den UserEntity almak
        final UserEntity userEntity = tokenConfirmationEntity.getUserEntity();
        // üyeliği aktif et
        userEntity.setIsAccountNonLocked(Boolean.TRUE);
        iUserRepository.save(userEntity);
        // Mail onaylanması sonrasında database Tokenı sil
        tokenServices.deleteToken(tokenConfirmationEntity.getId());
    }

    // TOKEN FIND
    @Override
    public Optional<TokenConfirmationEntity> findTokenConfirmation(String token) {
        return iTokenRepository.findTokenConfirmationEntityByToken(token);
    }

    //singUp(REGİSTER)
    @Override
    public UserDto signUp(UserDto userDto) {
        // DTO => ENTITY
        UserEntity userEntity = DtoToEntity(userDto);
        // Masking
        //userEntity.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(userEntity.getPassword()));
        UserEntity userEntity2 = iUserRepository.save(userEntity);
        // TOKEN OLUŞTUR
        TokenConfirmationEntity tokenConfirmationEntity = new TokenConfirmationEntity(userEntity2);
        String token = tokenServices.createToken(tokenConfirmationEntity);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("denememalatya4444@gmail.com");
        message.setTo(userDto.getEmail());
        message.setSentDate(new Date(System.currentTimeMillis()));
        message.setSubject("Üyeliğiniz Aktif olmasına son bir adım kaldı");
        message.setBcc("denememalatya4444@gmail.com");
        message.setCc("denememalatya4444@gmail.com");
        String mailContent = "Üyeliğinizi aktifleşmesine son bir adım lütfen linke tıklayınız. " + "http://localhost:2222/user/api/v1/confirm?token="+token;
        message.setText(mailContent);
        mailSender. send(message);
        return userDto;
    }

    //signIn(LOGİN)
    @Override
    public UserDto singIn(UserDto userDto) {
        return null;
    }

} //end clas
