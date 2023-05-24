package com.hamitmizrak.controller.api;

import com.hamitmizrak.business.dto.BlogDto;
import com.hamitmizrak.data.entity.BlogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

// NOT: interface için önemli bilgiler
// 1-) interface extends ile başka bir interface ekleyebilirsin. =>
// public interface IProfileHeaderApp extends IModelMapperService

// 2-) interface abstract ekleyerek implements eden class bütün metotları eklemez. =>
// abstract public interface IProfileHeaderApp
public interface IBlogGenericsApi<T> extends IProfileHeaderApp{

    // CREATE
    public ResponseEntity<T> blogApiCreate(T t);

    // LIST
    public ResponseEntity<List<T>> blogApiList();

    // FIND ID
    public ResponseEntity<?> blogApiFindById(Long id);

    // DELETE
    public ResponseEntity<Map<String, Boolean>> blogApiDeleteId(Long id);

    // UPDATE
    public ResponseEntity<T> blogApiUpdateId(Long id, T t);

    ///////////////////////////////////////

    // LIST PAGINATION
    // http://localhost:4444/api/v1/pagination?currentPage=0&pageSize=3
    ResponseEntity<Page<BlogEntity>> blogApiPagination(int currentPage, int pageSize);

    // LIST PAGINATION
    ResponseEntity<Page<BlogDto>> blogApiPageable(Pageable pageable, T t);
} //end class


