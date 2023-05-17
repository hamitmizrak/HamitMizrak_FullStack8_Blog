package com.hamitmizrak.controller.api;

import com.hamitmizrak.business.dto.RoleDto;
import com.hamitmizrak.business.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface IUserApi {

    public ResponseEntity<String> root();
    //////////////////////////////////

    // ROLES
    public ResponseEntity<RoleDto>  getRoles(RoleDto roleDto);

    //SIGN UP(Kayıt ol)
    public ResponseEntity<UserDto>  signUp(Integer rolesId, UserDto userDto);
    //////////////////////////////////

    // EMAIL CONFIRMATION
    public ResponseEntity<String> emailTokenConfirmation(String token);

    //SIGN IN (Giriş yap)
    public ResponseEntity<UserDto> singIn(UserDto userDto);

} // end class
