package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.business.dto.RoleDto;
import com.hamitmizrak.business.dto.UserDto;
import com.hamitmizrak.business.services.ITokenServices;
import com.hamitmizrak.business.services.IUserService;
import com.hamitmizrak.data.entity.RoleEntity;
import com.hamitmizrak.data.entity.TokenConfirmationEntity;
import com.hamitmizrak.data.entity.UserEntity;
import com.hamitmizrak.data.repository.IRoleRepository;
import com.hamitmizrak.data.repository.ITokenRepository;
import com.hamitmizrak.data.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

// LOMBOK
@RequiredArgsConstructor // injection

// SERVICE
@Service
@Component("userServiceImpl") //spring'in bir parçası olduğunu teyit ediyorum.
public class UserServicesImpl implements IUserService {

    // INJECTION
    private final ITokenRepository iTokenRepository; // Token oluşturma
    private final ITokenServices tokenServices;
    private final PasswordEncoderBean passwordEncoderBean; // şifre maskeleme
    private final ModelMapperBean modelMapperBean; // DTO => ENTITY

    @Autowired
    private JavaMailSender mailSender; // Mail oluşturma

    private final IUserRepository iUserRepository; // kullanıcı oluşturma
    private final IRoleRepository iRoleRepository;

    ////////////////////////////////////////
    @Value("${spring.mail.username}")
    private String serverMailAddress;

    ////////////////////////////////////////
    // MODEL MAPPER
    @Override
    public UserEntity DtoToEntity(UserDto userDto) {
        return modelMapperBean.modelMapperMethod().map(userDto, UserEntity.class);
    }

    @Override
    public UserDto EntityToDto(UserEntity userEntity) {
        return modelMapperBean.modelMapperMethod().map(userEntity, UserDto.class);
    }

    /////////////////////////////////////////////
    // Spring Security için Başında mutlaka ROLE_ olmalıdır
    // hasRole ve hasAnyRole için başına ROLE_ ekle
    // Spring Security için Başında mutlaka ROLE_ olmalıdır
    private final static String ROLE = "ROLE_";

    @Transactional // Create, delete, update için kullanmalısın
    @Override
    public RoleDto getRoles(RoleDto roleDto) {
        // RoleDto ==> RoleEntity
        RoleEntity roleEntity = modelMapperBean.modelMapperMethod().map(roleDto, RoleEntity.class);
        //roleEntity.setRoleName(ROLE.concat(roleEntity.getRoleName().toUpperCase()));
        roleEntity.setRoleName(roleEntity.getRoleName().toUpperCase());
        RoleEntity roleEntityData = iRoleRepository.save(roleEntity);
        // Set RoleDto
        roleDto.setRolesId(roleEntityData.getRolesId());
        //roleDto.setCreatedDate(roleEntityData.getCreatedDate());
        //roleDto.setUpdatedDate(roleEntityData.getUpdatedDate());
        //roleDto.setUpdatedUser(roleEntityData.getUpdatedUser());
        //roleDto.setCreatedUser(roleEntityData.getCreatedUser());
        return roleDto;
    }

    @Transactional // Create, delete, update için kullanmalısın
    @Override
    public UserDto signUp(Long rolesId, UserDto userDto) {
        UserEntity userEntity = modelMapperBean.modelMapperMethod().map(userDto, UserEntity.class);
        userEntity.getUserDetailsEmbeddable().setIsAccountNonLocked(userDto.getIsAccountNonLocked());
        userEntity.getUserDetailsEmbeddable().setIsEnabled(userDto.getIsEnabled());
        userEntity.getUserDetailsEmbeddable().setIsAccountNonExpired(userDto.getIsAccountNonExpired());
        userEntity.getUserDetailsEmbeddable().setIsCredentialsNonExpired(userDto.getIsCredentialsNonExpired());

        int dataInt=Integer.valueOf(Math.toIntExact(rolesId));
        RoleEntity roleEntity=iRoleRepository.findAll().get(dataInt-1 );
        Set<RoleEntity> rolList=new HashSet<>();
        rolList.add(roleEntity);
        userEntity.setRoles(rolList);
        userEntity.setPassword( passwordEncoderBean.passwordEncoderMethod().encode(userDto.getPassword()));
        iUserRepository.save(userEntity);

        //MAIL GONDER VE TOKEN OLUŞTUR
        // TOKEN OLUŞTUR
        TokenConfirmationEntity tokenConfirmationEntity = new TokenConfirmationEntity(userEntity);
        String token = tokenServices.createToken(tokenConfirmationEntity);
        SimpleMailMessage message = new SimpleMailMessage();
        System.out.println("APP 44 ==> "+serverMailAddress);
        message.setFrom(this.serverMailAddress);
        message.setTo(userDto.getEmail());
        message.setSentDate(new Date(System.currentTimeMillis()));
        message.setSubject("Üyeliğiniz Aktif olmasına son bir adım kaldı");
        //message.setBcc(this.serverMailAddress);
        //message.setCc(this.serverMailAddress);
        String mailContent = "Üyeliğinizi aktifleşmesine son bir adım lütfen linke tıklayınız. " + "http://localhost:2222/user/api/v1/confirm?token=" + token;
        message.setText(mailContent);
        mailSender.send(message);
        return userDto;
    }

    /////////////////////////////////////////////
    // ## IGenericUserService ############################################################
    @Transactional // Create, delete, update için kullanmalısın
    @Override
    public void emailTokenConfirmation(TokenConfirmationEntity tokenConfirmationEntity) {
        // @OneToOne(1-1) ilişkideki veriyi almak
        // TokenConfirmationEntity'den UserEntity almak
        final UserEntity userEntity = tokenConfirmationEntity.getUserEntity();
        // üyeliği aktif et
        // Embeddable eklediğim
        userEntity.getUserDetailsEmbeddable().setIsAccountNonLocked(Boolean.TRUE);
        iUserRepository.save(userEntity);
        // Mail onaylanması sonrasında database Tokenı sil
        tokenServices.deleteToken(tokenConfirmationEntity.getId());
    }

    // TOKEN FIND
    @Override
    public Optional<TokenConfirmationEntity> findTokenConfirmation(String token) {
        return iTokenRepository.findTokenConfirmationEntityByToken(token);
    }

    //signIn(LOGİN)
    @Override
    public UserDto singIn(UserDto userDto) {
        return null;
    }

} //end clas
