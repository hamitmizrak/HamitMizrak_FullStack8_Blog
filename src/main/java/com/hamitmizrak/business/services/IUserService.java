package com.hamitmizrak.business.services;

import com.hamitmizrak.business.dto.RoleDto;
import com.hamitmizrak.business.dto.UserDto;
import com.hamitmizrak.data.entity.TokenConfirmationEntity;
import com.hamitmizrak.data.entity.UserEntity;
import java.util.Optional;

public interface IUserService {

    // MODEL MAPPER
    public UserEntity DtoToEntity(UserDto userDto);

    public UserDto EntityToDto(UserEntity userEntity);

    ////////////////////////////////////
    // ROLES
    public RoleDto getRoles( RoleDto roleDto);

    //SIGN UP(Kayıt ol)
    public UserDto signUp( Long rolesId,  UserDto userDto);

    ////////////////////////////////////
    // EMAIL CONFIRMATION
    public void emailTokenConfirmation(TokenConfirmationEntity tokenConfirmationEntity);

    public Optional<TokenConfirmationEntity> findTokenConfirmation(String token);

    //SIGN IN (Giriş yap)
    public UserDto singIn(UserDto userDto);
} //end Class
