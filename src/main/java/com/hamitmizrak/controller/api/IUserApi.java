package com.hamitmizrak.controller.api;

import com.hamitmizrak.business.dto.UserDto;
import org.springframework.http.ResponseEntity;

// NOT: interface için önemli bilgiler
// 1-) interface extends ile başka bir interface ekleyebilirsin. =>
// public interface IProfileHeaderApp extends IModelMapperService

// 2-) interface abstract ekleyerek implements eden class bütün metotları eklemez. =>
// abstract public interface IProfileHeaderApp
public interface IUserApi {

    // ROOT
    public ResponseEntity<String> root();

    // USER CREATE(Kayıt ol)
    public ResponseEntity<UserDto>  userCreate(Long rolesId, UserDto userDto);

    //////////////////////////////////////////////////////////////////////////
    // EMAIL CONFIRMATION
    public ResponseEntity<String> emailTokenConfirmation(String token);

    //SIGN IN (Giriş yap)
    public ResponseEntity<UserDto> singIn(UserDto userDto);
} // end class