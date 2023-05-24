package com.hamitmizrak.business.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

// NOT: interface için önemli bilgiler
// 1-) interface extends ile başka bir interface ekleyebilirsin. =>
// public interface IProfileHeaderApp extends IModelMapperService

// 2-) interface abstract ekleyerek implements eden class bütün metotları eklemez. =>
// abstract public interface IProfileHeaderApp

public interface IProfileHeaderApp {

    // PROFILE
    public String profileService(String name);

    // Header Information
    public void headerService(Map<String, String> headers);

    // App Information
    public String appInformationService(HttpServletRequest request, HttpServletResponse response);

    // BÜTÜN VERİ EKLEMEK
    public String speedDataService();

    // DELETE ALL
    public String allDeleteService();
}
