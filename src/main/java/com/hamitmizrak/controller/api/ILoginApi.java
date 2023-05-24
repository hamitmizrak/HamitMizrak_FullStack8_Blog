package com.hamitmizrak.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;

// NOT: interface için önemli bilgiler
// 1-) interface extends ile başka bir interface ekleyebilirsin. =>
// public interface IProfileHeaderApp extends IModelMapperService

// 2-) interface abstract ekleyerek implements eden class bütün metotları eklemez. =>
// abstract public interface IProfileHeaderApp
public interface ILoginApi {

    public ResponseEntity<?> loginHandleAuthentication(String authorization);
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response);
}
