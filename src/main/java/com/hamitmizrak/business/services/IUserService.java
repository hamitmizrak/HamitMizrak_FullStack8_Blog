package com.hamitmizrak.business.services;
import com.hamitmizrak.business.dto.EmailDto;
import com.hamitmizrak.business.dto.UserDto;
import com.hamitmizrak.data.entity.TokenConfirmationEntity;
import com.hamitmizrak.data.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IUserService {

    // MODEL MAPPER
    public UserEntity DtoToEntity(UserDto userDto);
    public UserDto EntityToDto (UserEntity userEntity);

    // EMAIL CONFIRMATION
    public void emailTokenConfirmation(TokenConfirmationEntity tokenConfirmationEntity);

    public Optional<TokenConfirmationEntity> findTokenConfirmation(String token);

    //SIGN UP(Kayıt ol)
    public UserDto signUp(UserDto userDto);

    //SIGN IN (Giriş yap)
    public UserDto singIn(UserDto userDto);
}
