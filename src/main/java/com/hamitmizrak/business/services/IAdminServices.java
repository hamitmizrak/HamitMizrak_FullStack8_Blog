package com.hamitmizrak.business.services;

import com.hamitmizrak.business.dto.AdminDto;
import com.hamitmizrak.data.entity.AdminEntity;
import java.util.List;
import java.util.Map;

public interface IAdminServices {

    // SPEED DATA
    public AdminDto speedDataServices();

    // DELETE ALL
    public String allDeleteServices();

    // MODEL MAPPER
    public AdminDto EntityToDto(AdminEntity adminEntity);

    public AdminEntity DtoToEntity(AdminDto adminDto);

    // CREATE
    public AdminDto createServices(AdminDto customerDto);

    // LIST
    public List<AdminDto> listServices();

    // FIND ID
    public AdminDto findByIdServices(Long id);

    // DELETE
    public Map<String, Boolean> deleteServices(Long id);

    // UPDATE
    public AdminDto updateServices(Long id, AdminDto customerDto);
}
