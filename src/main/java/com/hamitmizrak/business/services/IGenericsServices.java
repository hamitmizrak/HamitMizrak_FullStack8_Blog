package com.hamitmizrak.business.services;

import com.hamitmizrak.data.entity.AdminEntity;
import java.util.List;
import java.util.Map;

public interface IGenericsServices<T> { //Generics

    // SPEED DATA
    public T speedDataServices();

    // DELETE ALL
    public String allDeleteServices();

    // MODEL MAPPER
    public T EntityToDto(AdminEntity adminEntity);

    public AdminEntity DtoToEntity(T t);

    // CREATE
    public T createServices(T t);

    // LIST
    public List<T> listServices();

    // FIND ID
    public T findByIdServices(Long id);

    // DELETE
    public Map<String, Boolean> deleteServices(Long id);

    // UPDATE
    public T updateServices(Long id, T t);
}
