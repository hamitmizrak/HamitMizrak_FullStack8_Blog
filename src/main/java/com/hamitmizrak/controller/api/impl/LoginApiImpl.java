package com.hamitmizrak.controller.api.impl;

import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.controller.api.ILoginApi;
import com.hamitmizrak.data.entity.UserEntity;
import com.hamitmizrak.data.repository.IUserRepository;
import com.hamitmizrak.error.ApiResult;
import com.hamitmizrak.exception.HamitMizrakException;
import com.hamitmizrak.util.FrontendURL;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// lombok
@RequiredArgsConstructor
@Log4j2

//rest
@RestController
@RequestMapping("/login/api/v1")
@CrossOrigin(origins = FrontendURL.FRONTEND_URL)
public class LoginApiImpl implements ILoginApi {

    // Injection
    private final IUserRepository iUserRepository;
    private final PasswordEncoderBean passwordEncoder;

    // 1.YOL
    // http://localhost:2222/login/api/v1/authentication
    // Header almak istersem @RequestHeader
    // Basic Authenticaton almak ==> Authorization
    @PostMapping("/authentication")
    @Override
    public ResponseEntity<?> loginHandleAuthentication(@RequestHeader(name = "Authorization", required = false) String authorization) {
        if (authorization == null) {
            //HttpStatus.UNAUTHORIZED : 401
            ApiResult apiResult = new ApiResult(401, "/api/v1.0/authentication44", "unauthorized44");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResult);
        }

        log.info(authorization);
        // Basic OmRmZw==
        // Basic sonrasında gelen Encoder almak için yani email ve root almak için
        // OmRmZw==
        String base64EncoderSplit = authorization.split("Basic ")[1];

        //Şifrelenmişi çözmek
        // email:password
        String base64Decoder = new String(Base64.getDecoder().decode(base64EncoderSplit));

        //email:password (iki noktaya göre split)
        String[] twoDotsSplit = base64Decoder.split(":");

        //email
        String email = twoDotsSplit[0]; //"hamitmizrak@gmail.com";
        System.out.println("Email: " + email);

        //password
        String password = twoDotsSplit[1];
        System.out.println("Şifre: " + password);

        //Eğer sistemde kullanıcı yoksa ResponseEntity 404 dönsün
        // UserEntity userEntity = iUserRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email+" bulunamadı"));
        Optional<UserEntity> userEntity = iUserRepository.findByEmail(email);
        if (userEntity.isPresent() == false) {
            //HttpStatus.UNAUTHORIZED : 401
            ApiResult apiResult = new ApiResult(404, "/login/api/v1/authentication", "Kullanıcı Bulunamadı");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResult);
            // Kullanıcı Kilitli mi ?
            // @Embeddable
        } else if (userEntity.isPresent() == true && userEntity.get().getUserDetailsEmbeddable().getIsAccountNonLocked() == false) {
            ApiResult apiResult = new ApiResult(401, "/login/api/v1/authentication", "Kullanıcı Kilitli");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResult);
        }
        /**
         else {
         //HttpStatus.UNAUTHORIZED : 401
         ApiResult apiResult = new ApiResult(401, "/login/api/v1", "yetkisiz giris");
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResult);
         }//else end
         */

        String passwordHash = userEntity.get().getPassword();
        //Hashli passwordu match ediyorum
        if (!passwordEncoder.passwordEncoderMethod().matches(password, passwordHash)) {
            ApiResult apiResult = new ApiResult(401, "/api/1.0/auth44", "unauthorized44");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResult);
        }
        //image
        Map<String, Object> resultUserRegisterEntity = new HashMap<>();
        resultUserRegisterEntity.put("name", userEntity.get().getName());
        resultUserRegisterEntity.put("surname", userEntity.get().getSurname());
        resultUserRegisterEntity.put("email", userEntity.get().getEmail());
        resultUserRegisterEntity.put("password", userEntity.get().getPassword());
        resultUserRegisterEntity.put("createdDate", userEntity.get().getSystemDate());
        //resultUserRegisterEntity.put("image",userEntity.getImage());

        // return ResponseEntity.ok().build();
        // return ResponseEntity.ok(userEntity);
        return ResponseEntity.ok(resultUserRegisterEntity);
    }//end 1.YOL

    // LOGIN LOGOUT
    // http://localhost:2222/logout
    @GetMapping("/logout")
    @Override
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        //Sayfaya giriş yapmış user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            return ResponseEntity.ok(ApiResult.builder().status(200).message("Çıkış yapıldı").path("/logout").build());
        }
        throw  new HamitMizrakException("Hata ! çıkış yapılamadı");
    }

} //end class
