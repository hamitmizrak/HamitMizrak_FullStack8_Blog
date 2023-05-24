package com.hamitmizrak.business.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

// NOT: interface için önemli bilgiler
// 1-) interface extends ile başka bir interface ekleyebilirsin. =>
// public interface IBlogGenericsServices extends IProfileHeaderApp

// 2-) interface abstract ekleyerek implements eden class bütün metotları eklemez. =>
// abstract public interface IBlogGenericsServices

// Generics
// D => Dto
// E => Entity
public interface IBlogGenericsServices<D,E> extends IProfileHeaderApp{ //Generics


    // MODEL MAPPER
    public D EntityToDto(E e);

    public E DtoToEntity(D d);
    ////////////////////////////////////////

    // CREATE
    public D blogServiceCreate(D d);

    // LIST
    public List<D> blogServiceList();

    // FIND ID
    public D blogServiceFindById(Long id);

    // DELETE
    public Map<String, Boolean> blogServiceDeleteId(Long id);

    // UPDATE
    public D blogServiceUpdateId(Long id, D d);
    ////////////////////////////////////////

    // LIST : Pageable
    public List<D> blogServiceAllList();

    // LIST : Pageable, page ,size
    public Page<E> blogServicePagination(int currentPage, int pageSize);

    // LIST : Pageable, Aktif Kullanıcı bilgisi: BlogDto
    public Page<D> blogServicePageable(Pageable pageable, D d);
}//end class
