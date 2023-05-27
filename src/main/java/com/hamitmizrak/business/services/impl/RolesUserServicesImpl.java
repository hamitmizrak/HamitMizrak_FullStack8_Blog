package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.business.dto.RoleDto;
import com.hamitmizrak.business.services.IRolesUserService;
import com.hamitmizrak.data.entity.RoleEntity;
import com.hamitmizrak.data.repository.IRoleRepository;
import com.hamitmizrak.exception.HamitMizrakException;
import com.hamitmizrak.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

// LOMBOK
@RequiredArgsConstructor // injection

// SERVICE
@Service
@Component("rolesUserServiceImpl") //spring'in bir parçası olduğunu teyit ediyorum.
public class RolesUserServicesImpl implements IRolesUserService {

    // INJECTION
    private final ModelMapperBean modelMapperBean; // DTO => ENTITY
    private final IRoleRepository iRoleRepository;

    // MODEL MAPPER
    @Override
    public RoleEntity DtoToEntity(RoleDto userDto) {
        return modelMapperBean.modelMapperMethod().map(userDto, RoleEntity.class);
    }

    @Override
    public RoleDto EntityToDto(RoleEntity roleEntity) {
        return modelMapperBean.modelMapperMethod().map(roleEntity, RoleDto.class);
    }

    @Override
    public List<RoleDto> rolesList() {
        List<RoleEntity>  roleEntity=iRoleRepository.findAll();
        List<RoleDto> roleDtoList=new ArrayList<>();
        for (RoleEntity temp: roleEntity) {
            RoleDto roleDto=modelMapperBean.modelMapperMethod().map(temp,RoleDto.class);
            roleDtoList.add(roleDto);
        }
        return roleDtoList ;
    }

    /////////////////////////////////////////////
    // Spring Security için Başında mutlaka ROLE_ olmalıdır
    // hasRole ve hasAnyRole için başına ROLE_ ekle
    // Spring Security için Başında mutlaka ROLE_ olmalıdır
    // private final static String ROLE = "ROLE_";

    @Transactional // Create, delete, update için kullanmalısın
    @Override
    public RoleDto rolesCreate(RoleDto roleDto) {
        // RoleDto ==> RoleEntity
        RoleEntity roleEntity = modelMapperBean.modelMapperMethod().map(roleDto, RoleEntity.class);
        //roleEntity.setRoleName(ROLE.concat(roleEntity.getRoleName().toUpperCase()));
        roleEntity.setRoleName(roleEntity.getRoleName().toUpperCase());
        RoleEntity roleEntityData = iRoleRepository.save(roleEntity);
        // Set RoleDto
        roleDto.setRolesId(roleEntityData.getRolesId());
        return roleDto;
    }

    // FIND
    @Override
    public RoleDto rolesFind(Long id) {
         /*
         // 1.YOL
         Optional<BlogEntity> findEntity= iBlogRepository.findById(id);
         //Model Mapper
         BlogDto findDto= EntityToDto(findEntity.get());
         //findEntity.isPresent() VEYA findEntity!=null
         if(findEntity.isPresent()){
         return findDto;
         }
         */
        boolean result=iRoleRepository.findById(id).isPresent();

        // 2.YOL
        RoleEntity roleEntity = null;
        if (id != null){ // iRoleRepository.findById(id).isPresent()
            roleEntity = iRoleRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id + " nolu ID yoktur"));
        } else if (id == null) {
            throw new HamitMizrakException("Roles Dto Null geldi");
        }
        return EntityToDto(roleEntity);
    }

    // ROLE UPDATE
    @Override
    @Transactional // Create, delete, update için kullanmalısın
    public RoleDto rolesUpdate(Long id, RoleDto roleDto) {
        // FIND
        RoleDto roleDtoFind = rolesFind(id);
        // Dto ==> Entity
        RoleEntity roleEntity = DtoToEntity(roleDtoFind);
        if (roleEntity != null) {
            roleEntity.setRoleName(roleDto.getRoleName());
            iRoleRepository.save(roleEntity);
            // Kaydedilen verinin ID dönsün
            roleDto.setId(roleEntity.getRolesId());
            roleDto.setCreatedDate(roleEntity.getCreatedDate());
        }
        return roleDto;
    }


    ///////////////////////////////////////////////////////////////////////////
    // Email(User) adresinde RoleName(Role) bulmak
    @Override
    public RoleDto userEmailFindRoles(String emailAddress) {
        RoleEntity roleEntity=  iRoleRepository.userEmailFindRoles(emailAddress);
        System.out.println(roleEntity);
        System.out.println(roleEntity.getRolesId());
        System.out.println(roleEntity.getRoleName());
        return modelMapperBean.modelMapperMethod().map(roleEntity,RoleDto.class);
    }

    // @ManyToMany N-M Delete
    // DELETE
    @Override
    @Transactional // create,delete,update
    public RoleDto rolesDelete(Long id) {
        // FIND
        RoleDto roleDto = rolesFind(id);
        // Dto ==> Entity
        RoleEntity roleEntity = DtoToEntity(roleDto);
        if (roleEntity != null) {
            iRoleRepository.delete(roleEntity);
        }
        return roleDto;
    }

} //end clas
