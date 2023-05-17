package com.hamitmizrak.controller.api;

import com.hamitmizrak.business.dto.UserDto;
import org.springframework.http.ResponseEntity;
public interface IUserApi {

    public ResponseEntity<String>  root();

    // EMAIL CONFIRMATION
    public ResponseEntity<String>  emailTokenConfirmation(String token);

    //SIGN UP(Kayıt ol)
    public ResponseEntity<UserDto>  signUp(UserDto userDto);

    //SIGN IN (Giriş yap)
    public ResponseEntity<UserDto>  singIn(UserDto userDto);
} // end class
