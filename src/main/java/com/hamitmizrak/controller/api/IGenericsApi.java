package com.hamitmizrak.controller.api;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IGenericsApi<T> {

    //SPEED DATA
    public ResponseEntity<T>  speedDataApi();

    // DELETE ALL
    public ResponseEntity<String>  deleteApi();

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
