package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.business.dto.AdminDto;
import com.hamitmizrak.business.services.IGenericsServices;
import com.hamitmizrak.data.entity.AdminEntity;
import com.hamitmizrak.data.repository.IAdminRepository;
import com.hamitmizrak.exception.HamitMizrakException;
import com.hamitmizrak.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

// LOMBOK
@RequiredArgsConstructor // injection
@Log4j2
//@SneakyThrows //throws

@Service
// Asıl iş yükünü yapan class
public class AdminServices implements IGenericsServices<AdminDto> {

    // INJECTION
    private final IAdminRepository iCustomerRepository;
    private final ModelMapperBean modelMapperBean;
    private final PasswordEncoderBean passwordEncoderBean;

    // SPEED DATA
    @Override
    public AdminDto speedDataServices() {
        AdminDto customerDto=null;
        for (int i = 1; i <= 5; i++) {
             customerDto = AdminDto.builder()
                    .name("adı " + i)
                    .surname("soyadı " + i)
                    .email(UUID.randomUUID().toString().concat("@gmail.com"))
                    .password("root")
                    .build();
            customerDto.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(customerDto.getPassword()));
            // DTO ==> ENTITY Model Mapper
            AdminEntity customerEntityMapper = DtoToEntity(customerDto);
            AdminEntity customerEntityRepository = iCustomerRepository.save(customerEntityMapper);
        }
        return customerDto;
    }


    // DELETE ALL
    @Override
    public String allDeleteServices() {
        iCustomerRepository.deleteAll();
        return  "Bütün veriler silindi";
    }

    //MODEL MAPPER
    @Override
    public AdminDto EntityToDto(AdminEntity customerEntity) {
        return modelMapperBean.modelMapperMethod().map(customerEntity, AdminDto.class);
    }

    @Override
    public AdminEntity DtoToEntity(AdminDto customerDto) {
        return modelMapperBean.modelMapperMethod().map(customerDto, AdminEntity.class);
    }

    // CREATE
    @Override
    @Transactional // create,delete,update
    public AdminDto createServices(AdminDto customerDto) {
        if (customerDto != null) {
            // Passwork Masking
            customerDto.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(customerDto.getPassword()));
            // DTO ==> ENTITY Model Mapper
            AdminEntity customerEntityMapper = DtoToEntity(customerDto);
            AdminEntity customerEntityRepository = iCustomerRepository.save(customerEntityMapper);
            // Kaydedilen verinin ID dönsün
            customerDto.setId(customerEntityRepository.getId());
            customerDto.setCreatedDate(customerEntityRepository.getCreatedDate());
        } else if (customerDto == null) {
            throw new HamitMizrakException("Customer Dto Null geldi");
        }
        return customerDto;
    }

    // LIST
    @Override
    public List<AdminDto> listServices() {
        Iterable<AdminEntity> customerEntityList = iCustomerRepository.findAll();
        // EntityList ==> DtoList çevir
        List<AdminDto> entityToDtoList = new ArrayList<>();
        for (AdminEntity temp : customerEntityList) {
            AdminDto customerDto = EntityToDto(temp);
            //byte[] decodeAccessKeyPin = Base64.getDecoder().decode(passwordEncoderBean.passwordEncoderMethod().encode(customerDto.getPassword()));
            //String decodeString = new String(decodeAccessKeyPin);
            //customerDto.setPassword(decodeString);
            entityToDtoList.add(customerDto);
        }
        return entityToDtoList;
    }

    // FIND
    @Override
    public AdminDto findByIdServices(Long id) {
        AdminEntity customerEntity = null;
        if (id != null) {
            customerEntity = iCustomerRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id + " nolu ID yoktur"));
        } else if (id == null) {
            throw new HamitMizrakException("Customer Dto Null geldi");
        }
        return EntityToDto(customerEntity);
    }

    // DELETE
    @Override
    @Transactional // create,delete,update
    public Map<String, Boolean> deleteServices(Long id) {
        Map<String, Boolean> mapDelete = new HashMap<>();
        // FIND
        AdminDto customerDto = findByIdServices(id);
        // Dto ==> Entity
        AdminEntity customerEntity = DtoToEntity(customerDto);
        if (customerEntity != null) {
            iCustomerRepository.delete(customerEntity);
            mapDelete.put("Silindi", Boolean.TRUE);
        }
        return mapDelete;
    }

    // UPDATE
    @Override
    @Transactional // create,delete,update
    public AdminDto updateServices(Long id, AdminDto customerDto) {
        // FIND
        AdminDto customerDtoFind = findByIdServices(id);
        // Dto ==> Entity
        AdminEntity customerEntity = DtoToEntity(customerDtoFind);
        if (customerEntity != null) {
            customerEntity.setName(customerDto.getName());
            customerEntity.setSurname(customerDto.getSurname());
            customerEntity.setEmail(customerDto.getEmail());
            customerEntity.setImage(customerDto.getImage());
            customerEntity.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(customerDto.getPassword()));
            iCustomerRepository.save(customerEntity);
            // Kaydedilen verinin ID dönsün
            customerDto.setId(customerEntity.getId());
            customerDto.setCreatedDate(customerEntity.getCreatedDate());
        }
        return customerDto;
    }
} //end class
