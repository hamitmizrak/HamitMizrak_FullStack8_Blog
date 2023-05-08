package com.hamitmizrak.business.services;

import com.hamitmizrak.data.entity.RegisterEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

public interface IGenericsServices<D,E> { //Generics
    // PROFILE
    public String getProfile(String name);

    // Header Information
    public void getAllHeaderData(Map<String, String> headers);

    // App Information
    public String getAppInformation(HttpServletRequest request, HttpServletResponse response);
    ////////////////////////////////////////

    // SPEED DATA
    public D speedDataServices();

    // DELETE ALL
    public String allDeleteServices();
    ////////////////////////////////////////

    // MODEL MAPPER
    public D EntityToDto(E e);

    public E DtoToEntity(D d);
    ////////////////////////////////////////

    // CREATE
    public D createServices(D d);

    // LIST
    public List<D> listServices();

    // FIND ID
    public D findByIdServices(Long id);

    // DELETE
    public Map<String, Boolean> deleteServices(Long id);

    // UPDATE
    public D updateServices(Long id, D d);
    ////////////////////////////////////////

    // LIST : Pageable
    public List<D> getAllRegisters();

    // LIST : Pageable, page ,size
    public Page<RegisterEntity> getAllRegisterPaginationEntity(int currentPage, int pageSize);

    // LIST : Pageable, Aktif Kullanıcı bilgisi: RegisterDto
    public Page<D> getAllRegisterPaginationEntityPageable(Pageable pageable, D d);
}
