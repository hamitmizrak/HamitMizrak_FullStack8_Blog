package com.hamitmizrak.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;

// Authentication: Kimlik Doğrulama
// Authorization : Kimlik Yetkilendirme
public interface ILoginApi {

    // LOGIN Basic Authentication
    public ResponseEntity<?> loginHandleAuthentication(String authorization);

    // LOGOUT
    // import jakarta.servlet.http.HttpServletRequest;
    // import jakarta.servlet.http.HttpServletResponse;
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response);
}
