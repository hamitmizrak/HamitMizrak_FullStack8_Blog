package com.hamitmizrak.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

// NOT: interface için önemli bilgiler
// 1-) interface extends ile başka bir interface ekleyebilirsin. =>
// public interface IProfileHeaderApp extends IModelMapperService

// 2-) interface abstract ekleyerek implements eden class bütün metotları eklemez. =>
// abstract public interface IProfileHeaderApp

public interface IProfileHeaderApp {

    public ResponseEntity<String> getRoot();
    /////////////////////////////////////////

    //PROFILE
    public ResponseEntity<String> profileApi(String name);

    //Header Information
    public ResponseEntity<?> headerApi(Map<String, String> headers);

    //App Information
    public ResponseEntity<?> appInformationApi(HttpServletRequest request, HttpServletResponse response);
    ///////////////////////////////////////

    //SPEED DATA
    public ResponseEntity<?> speedDataApi();

    // DELETE ALL
    public ResponseEntity<String> allDeleteApi();

}
