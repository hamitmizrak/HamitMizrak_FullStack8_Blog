package com.hamitmizrak.controller.api.impl;

import com.hamitmizrak.business.dto.UserDto;
import com.hamitmizrak.business.services.IUserService;
import com.hamitmizrak.controller.api.IUserApi;
import com.hamitmizrak.data.entity.TokenConfirmationEntity;
import com.hamitmizrak.error.ApiResult;
import com.hamitmizrak.util.FrontendURL;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// REST
@RestController
@CrossOrigin(origins = FrontendURL.FRONTEND_URL)// @CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user/api/v1")
public class UserApiImpl implements IUserApi {

    // INJECTION
    private final IUserService iUserService;

    // ERROR
    private ApiResult apiResult;

    // ROOT
    // http://localhost:2222/user/api/v1
    @Override
    @GetMapping("/")
    public ResponseEntity<String> root() {
        return ResponseEntity.status(200).body("index");
    }
    //////////////////////////////////////////////////////////

    // REGISTER CREATE
    // http://localhost:2222/user/api/v1/create/1
    @PostMapping("/create/{path_id}")
    @Override
    public ResponseEntity<UserDto> userCreate(@PathVariable(name="path_id") Long rolesId, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(iUserService.userCreate(rolesId,userDto));
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