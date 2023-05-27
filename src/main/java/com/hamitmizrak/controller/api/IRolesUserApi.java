package com.hamitmizrak.controller.api;

import com.hamitmizrak.business.dto.RoleDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

// NOT: interface için önemli bilgiler
// 1-) interface extends ile başka bir interface ekleyebilirsin. =>
// public interface IProfileHeaderApp extends IModelMapperService

// 2-) interface abstract ekleyerek implements eden class bütün metotları eklemez. =>
// abstract public interface IProfileHeaderApp
public interface IRolesUserApi {

    // ROLES CREATE
    public ResponseEntity<RoleDto> rolesCreate(RoleDto roleDto);

    // ROLES LIST
    public ResponseEntity<List<RoleDto>> rolesList();

    // FIND
    ResponseEntity<?> rolesApiFindById(Long id);

    // UPDATE
    ResponseEntity<RoleDto> rolesUpdate(Long id, RoleDto roleDto);

    ////////////////////////////////////////////////////////////////////////
    //Email adresinde kullanı rolünü bulmak
    ResponseEntity<RoleDto> userEmailFindRoles(String emailAddress);

    // ROLE DELETE
    ResponseEntity<?> rolesDelete(Long id);

} // end class