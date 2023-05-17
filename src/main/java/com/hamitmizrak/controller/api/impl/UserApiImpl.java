package com.hamitmizrak.controller.api.impl;

import com.hamitmizrak.business.dto.RoleDto;
import com.hamitmizrak.business.dto.UserDto;
import com.hamitmizrak.business.services.IUserService;
import com.hamitmizrak.controller.api.IUserApi;
import com.hamitmizrak.data.entity.TokenConfirmationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

// LOMBOK
@RequiredArgsConstructor

// REST
@RestController
@CrossOrigin
@RequestMapping("/user/api/v1")
public class UserApiImpl implements IUserApi {

    // INJECTION
    private final IUserService iUserService;

    // ROOT
    // http://localhost:2222/user/api/v1
    @Override
    @GetMapping("/")
    public ResponseEntity<String> root() {
        return ResponseEntity.status(200).body("index");
    }

    /////////////////////////////////////////////////////////
    // http://localhost:2222/user/api/v1/roles
    @PostMapping("/roles")
    @Override
    public ResponseEntity<RoleDto> getRoles(@RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(iUserService.getRoles(roleDto));
    }

    // http://localhost:2222/user/api/v1/user/1
    @PostMapping("/user/{path_id}")
    @Override
    public ResponseEntity<UserDto> signUp(@PathVariable(name="path_id") Integer rolesId, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(iUserService.signUp(rolesId,userDto));
    }

    /////////////////////////////////////////////////////////
    //http://localhost:2222/user/api/v1/confirm?token=ca478481-5f7a-406b-aaa4-2012ebe1afb4
    @Override
    @GetMapping("/confirm")
    public ResponseEntity<String> emailTokenConfirmation(@RequestParam("token") String token) {
        Optional<TokenConfirmationEntity> tokenConfirmationEntity = iUserService.findTokenConfirmation(token);
        tokenConfirmationEntity.ifPresent(iUserService::emailTokenConfirmation);
        return ResponseEntity.ok("Üyeliğiniz Aktif olunmuştur. Ana sayafa için tıklayınız http://localhost:2222");
    }


    //http://localhost:2222/user/api/v1/signin
    @Override
    @PostMapping("/signin")
    public ResponseEntity<UserDto> singIn(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userDto);
    }

} // end class