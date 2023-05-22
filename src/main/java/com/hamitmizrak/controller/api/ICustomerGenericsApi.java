package com.hamitmizrak.controller.api;

import com.hamitmizrak.business.dto.CustomerDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ICustomerGenericsApi<T> {

    //PROFILE
    public String getProfile(String name);

    //Header Information
    public void getAllHeaderData(Map<String, String> headers);

    //App Information
    public ResponseEntity<?> getAppInformation(HttpServletRequest request, HttpServletResponse response);
    ///////////////////////////////////////

    //SPEED DATA
    public ResponseEntity<T> speedDataApi();

    // DELETE ALL
    public ResponseEntity<String> deleteApi();
    ///////////////////////////////////////

    // CREATE
    public ResponseEntity<T> createApi(T t);

    // LIST
    public ResponseEntity<List<T>> listApi();

    // FIND ID
    public ResponseEntity<?> findByIdApi(Long id);

    // DELETE
    public ResponseEntity<Map<String, Boolean>> deleteApi(Long id);

    // UPDATE
    public ResponseEntity<T> updateApi(Long id, T t);

    ///////////////////////////////////////

    // LIST PAGINATION
    // http://localhost:4444/api/v1/pagination?currentPage=0&pageSize=3
    ResponseEntity<Page<CustomerDto>> getAllCustomerPaginationEntity(int currentPage, int pageSize);

    // LIST PAGINATION
    ResponseEntity<Page<CustomerDto>> getAllCustomerPaginationEntityPageable(Pageable pageable, T t);
}


