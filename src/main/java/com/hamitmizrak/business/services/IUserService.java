package com.hamitmizrak.business.services;

import com.hamitmizrak.business.dto.UserDto;
import com.hamitmizrak.data.entity.TokenConfirmationEntity;
import com.hamitmizrak.data.entity.UserEntity;
import java.util.Optional;

public interface IUserService {

    // MODEL MAPPER
    public UserEntity DtoToEntity(UserDto userDto);

    public UserDto EntityToDto(UserEntity userEntity);

    ////////////////////////////////////

    //SIGN UP(Kayıt ol)
    public UserDto userCreate( Long rolesId,  UserDto userDto);

    ////////////////////////////////////
    // EMAIL CONFIRMATION
    // EMAIL TOKEN CONFIRMATION
    public void emailTokenConfirmation(TokenConfirmationEntity tokenConfirmationEntity);

    // EMAIL TOKEN FIND
    public Optional<TokenConfirmationEntity> findTokenConfirmation(String token);

    //SIGN IN (Giriş yap)
    public UserDto singIn(UserDto userDto);
} //end Class
