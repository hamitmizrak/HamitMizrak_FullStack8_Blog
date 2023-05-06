package com.hamitmizrak.controller.api;

import com.hamitmizrak.business.dto.AdminDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IAdminApi {

    //SPEED DATA
    public ResponseEntity<AdminDto>  speedDataApi();

    // DELETE ALL
    public ResponseEntity<String>  deleteApi();

    // CREATE
    public ResponseEntity<AdminDto>  createApi(AdminDto customerDto);

    // LIST
    public ResponseEntity<List<AdminDto>>  listApi();

    // FIND ID
    public ResponseEntity<?>  findByIdApi(Long id);

    // DELETE
    public ResponseEntity<Map<String,Boolean>>  deleteApi(Long id);

    // UPDATE
    public ResponseEntity<AdminDto> updateApi(Long id, AdminDto customerDto);
}
