package com.hamitmizrak.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IGenericsApi<T> {

    //PROFILE
    public String getProfile(String name);

    //Header Information
    public void getAllHeaderData(Map<String,String> headers);
    //App Information
    public ResponseEntity<?> getAppInformation(HttpServletRequest request, HttpServletResponse response);
    ///////////////////////////////////////

    //SPEED DATA
    public ResponseEntity<T>  speedDataApi();

    // DELETE ALL
    public ResponseEntity<String>  deleteApi();

    ///////////////////////////////////////
    // CREATE
    public ResponseEntity<T>  createApi(T t);

    // LIST
    public ResponseEntity<List<T>>  listApi();

    // FIND ID
    public ResponseEntity<?>  findByIdApi(Long id);

    // DELETE
    public ResponseEntity<Map<String,Boolean>>  deleteApi(Long id);

    // UPDATE
    public ResponseEntity<T> updateApi(Long id, T t);
}
