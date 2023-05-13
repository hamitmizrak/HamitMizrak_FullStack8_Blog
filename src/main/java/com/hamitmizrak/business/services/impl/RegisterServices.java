package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.business.dto.RegisterDto;
import com.hamitmizrak.business.services.IGenericsServices;
import com.hamitmizrak.data.entity.RegisterEntity;
import com.hamitmizrak.data.repository.IRegisterRepository;
import com.hamitmizrak.exception.HamitMizrakException;
import com.hamitmizrak.exception.ResourceNotFoundException;
import com.hamitmizrak.profile.IChooiseProfile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

// LOMBOK
@RequiredArgsConstructor // injection
@Log4j2
//@SneakyThrows //throws

@Service
// Asıl iş yükünü yapan class
public class RegisterServices implements IGenericsServices<RegisterDto, RegisterEntity> {

    // INJECTION
    private final IRegisterRepository iRegisterRepository;
    private final ModelMapperBean modelMapperBean;
    private final PasswordEncoderBean passwordEncoderBean;
    private final IChooiseProfile profile;

    ////////////////////////////////////////
    // getProfile
    @Override
    public String getProfile(String name) {
        log.info(profile.message(name));
        return profile.message(name);
    }

    // getAllHeaderData
    @Override
    public void getAllHeaderData(Map<String, String> headers) {
        headers.forEach((key, value) -> {
            System.out.println("HeaderName: " + key + " HeaderValue: " + value);
        });
    }

    //App Information
     @Override
     public String getAppInformation(HttpServletRequest request, HttpServletResponse response) {
        String URI = request.getRequestURI();
        String LOCALHOST = request.getLocalAddr();
        String SESSION = request.getSession().toString();
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("uri: "+URI).append("<br/>localhost: "+LOCALHOST).append("<br/>session: "+SESSION);
        String informationToString=stringBuilder.toString();
        return informationToString;
    }
    ////////////////////////////////////////
    // SPEED DATA
    @Override
    public RegisterDto speedDataServices() {
        RegisterDto customerDto = null;
        for (int i = 1; i <= 5; i++) {
            customerDto = RegisterDto.builder()
                    .name("adı " + i)
                    .surname("soyadı " + i)
                    .email(UUID.randomUUID().toString().concat("@gmail.com"))
                    .password("root")
                    .build();
            customerDto.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(customerDto.getPassword()));
            // DTO ==> ENTITY Model Mapper
            RegisterEntity customerEntityMapper = DtoToEntity(customerDto);
            RegisterEntity customerEntityRepository = iRegisterRepository.save(customerEntityMapper);
        }
        return customerDto;
    }

    // DELETE ALL
    @Override
    public String allDeleteServices() {
        iRegisterRepository.deleteAll();
        return "Bütün veriler silindi";
    }
    ////////////////////////////////////////
    //MODEL MAPPER
    @Override
    public RegisterDto EntityToDto(RegisterEntity customerEntity) {
        return modelMapperBean.modelMapperMethod().map(customerEntity, RegisterDto.class);
    }

    @Override
    public RegisterEntity DtoToEntity(RegisterDto customerDto) {
        return modelMapperBean.modelMapperMethod().map(customerDto, RegisterEntity.class);
    }
    ////////////////////////////////////////
    // CREATE
    @Override
    @Transactional // create,delete,update
    public RegisterDto createServices(RegisterDto registerDto) {
        if (registerDto != null) {
            // Passwork Masking
            registerDto.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(registerDto.getPassword()));
            // DTO ==> ENTITY Model Mapper
            RegisterEntity customerEntityMapper = DtoToEntity(registerDto);
            RegisterEntity customerEntityRepository = iRegisterRepository.save(customerEntityMapper);
            // Kaydedilen verinin ID dönsün
            registerDto.setId(customerEntityRepository.getId());
            registerDto.setCreatedDate(customerEntityRepository.getCreatedDate());
        } else if (registerDto == null) {
            throw new HamitMizrakException("Register Dto Null geldi");
        }
        return registerDto;
    }

    // LIST
    @Override
    public List<RegisterDto> listServices() {
        Iterable<RegisterEntity> registerEntityList = iRegisterRepository.findAll();
        // EntityList ==> DtoList çevir
        List<RegisterDto> entityToDtoList = new ArrayList<>();
        for (RegisterEntity temp : registerEntityList) {
            RegisterDto registerDto = EntityToDto(temp);
            //byte[] decodeAccessKeyPin = Base64.getDecoder().decode(passwordEncoderBean.passwordEncoderMethod().encode(customerDto.getPassword()));
            //String decodeString = new String(decodeAccessKeyPin);
            //customerDto.setPassword(decodeString);
            entityToDtoList.add(registerDto);
        }
        return entityToDtoList;
    }

    // FIND
    @Override
    public RegisterDto findByIdServices(Long id) {

         /*
         // 1.YOL
         Optional<RegisterEntity> findEntity= iRegisterRepository.findById(id);
         //Model Mapper
         RegisterDto findDto= EntityToDto(findEntity.get());
         //findEntity.isPresent() VEYA findEntity!=null
         if(findEntity.isPresent()){
         return findDto;
         }
         */

        // 2.YOL
        RegisterEntity registerEntity = null;
        if (id != null) {
            registerEntity = iRegisterRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id + " nolu ID yoktur"));
        } else if (id == null) {
            throw new HamitMizrakException("Customer Dto Null geldi");
        }
        return EntityToDto(registerEntity);
    }

    // DELETE
    @Override
    @Transactional // create,delete,update
    public Map<String, Boolean> deleteServices(Long id) {
        Map<String, Boolean> mapDelete = new HashMap<>();
        // FIND
        RegisterDto registerDto = findByIdServices(id);
        // Dto ==> Entity
        RegisterEntity customerEntity = DtoToEntity(registerDto);
        if (customerEntity != null) {
            iRegisterRepository.delete(customerEntity);
            mapDelete.put("Silindi", Boolean.TRUE);
        }
        return mapDelete;
    }

    // UPDATE
    @Override
    @Transactional // create,delete,update
    public RegisterDto updateServices(Long id, RegisterDto customerDto) {
        // FIND
        RegisterDto registerDtoFind = findByIdServices(id);
        // Dto ==> Entity
        RegisterEntity registerEntity = DtoToEntity(registerDtoFind);
        if (registerEntity != null) {
            registerEntity.setName(customerDto.getName());
            registerEntity.setSurname(customerDto.getSurname());
            registerEntity.setEmail(customerDto.getEmail());
            registerEntity.setImage(customerDto.getImage());
            registerEntity.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(customerDto.getPassword()));
            iRegisterRepository.save(registerEntity);
            // Kaydedilen verinin ID dönsün
            customerDto.setId(registerEntity.getId());
            customerDto.setCreatedDate(registerEntity.getCreatedDate());
        }
        return customerDto;
    }

    ////////////////////////////////////////
    @Override
    public List<RegisterDto> getAllRegisters() {
        return null;
    }

    @Override
    public Page<RegisterDto> getAllRegisterPaginationEntity(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<RegisterEntity>  registerEntity=iRegisterRepository.findAll(pageable);
        Page<RegisterDto> dto= null;
        return dto;
    }

    @Override
    public Page<RegisterDto> getAllRegisterPaginationEntityPageable(Pageable pageable, RegisterDto registerDto) {
        return null;
    }
} //end class
