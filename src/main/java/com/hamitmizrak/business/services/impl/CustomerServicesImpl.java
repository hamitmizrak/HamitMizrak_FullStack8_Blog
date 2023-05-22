package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.business.dto.CustomerDto;
import com.hamitmizrak.business.services.ICustomerGenericsServices;
import com.hamitmizrak.data.entity.CustomerEntity;
import com.hamitmizrak.data.repository.ICustomerRepository;
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
public class CustomerServicesImpl implements ICustomerGenericsServices<CustomerDto, CustomerEntity> {

    // INJECTION
    private final ICustomerRepository iCustomerRepository;
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
    public CustomerDto speedDataServices() {
        CustomerDto customerDto = null;
        for (int i = 1; i <= 5; i++) {
            customerDto = CustomerDto.builder()
                    .name("adı " + i)
                    .surname("soyadı " + i)
                    .email(UUID.randomUUID().toString().concat("@gmail.com"))
                    .password("root")
                    .build();
            customerDto.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(customerDto.getPassword()));
            // DTO ==> ENTITY Model Mapper
            CustomerEntity customerEntityMapper = DtoToEntity(customerDto);
            CustomerEntity customerEntityRepository = iCustomerRepository.save(customerEntityMapper);
        }
        return customerDto;
    }

    // DELETE ALL
    @Override
    public String allDeleteServices() {
        iCustomerRepository.deleteAll();
        return "Bütün veriler silindi";
    }
    ////////////////////////////////////////
    //MODEL MAPPER
    @Override
    public CustomerDto EntityToDto(CustomerEntity customerEntity) {
        return modelMapperBean.modelMapperMethod().map(customerEntity, CustomerDto.class);
    }

    @Override
    public CustomerEntity DtoToEntity(CustomerDto customerDto) {
        return modelMapperBean.modelMapperMethod().map(customerDto, CustomerEntity.class);
    }
    ////////////////////////////////////////
    // CREATE
    @Override
    @Transactional // create,delete,update
    public CustomerDto createServices(CustomerDto customerDto) {
        if (customerDto != null) {
            // Passwork Masking
            customerDto.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(customerDto.getPassword()));
            // DTO ==> ENTITY Model Mapper
            CustomerEntity customerEntityMapper = DtoToEntity(customerDto);
            CustomerEntity customerEntityRepository = iCustomerRepository.save(customerEntityMapper);
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
    public List<CustomerDto> listServices() {
        Iterable<CustomerEntity> customerEntityList = iCustomerRepository.findAll();
        // EntityList ==> DtoList çevir
        List<CustomerDto> entityToDtoList = new ArrayList<>();
        for (CustomerEntity temp : customerEntityList) {
            CustomerDto customerDto = EntityToDto(temp);
            //byte[] decodeAccessKeyPin = Base64.getDecoder().decode(passwordEncoderBean.passwordEncoderMethod().encode(customerDto.getPassword()));
            //String decodeString = new String(decodeAccessKeyPin);
            //customerDto.setPassword(decodeString);
            entityToDtoList.add(customerDto);
        }
        return entityToDtoList;
    }

    // FIND
    @Override
    public CustomerDto findByIdServices(Long id) {

         /*
         // 1.YOL
         Optional<CustomerEntity> findEntity= iCustomerRepository.findById(id);
         //Model Mapper
         CustomerDto findDto= EntityToDto(findEntity.get());
         //findEntity.isPresent() VEYA findEntity!=null
         if(findEntity.isPresent()){
         return findDto;
         }
         */

        // 2.YOL
        CustomerEntity customerEntity = null;
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
        CustomerDto customerDto = findByIdServices(id);
        // Dto ==> Entity
        CustomerEntity customerEntity = DtoToEntity(customerDto);
        if (customerEntity != null) {
            iCustomerRepository.delete(customerEntity);
            mapDelete.put("Silindi", Boolean.TRUE);
        }
        return mapDelete;
    }

    // UPDATE
    @Override
    @Transactional // create,delete,update
    public CustomerDto updateServices(Long id, CustomerDto customerDto) {
        // FIND
        CustomerDto customerDtoFind = findByIdServices(id);
        // Dto ==> Entity
        CustomerEntity customerEntity = DtoToEntity(customerDtoFind);
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

    ////////////////////////////////////////
    @Override
    public List<CustomerDto> getAllCustomers() {
        return null;
    }

    @Override
    public Page<CustomerDto> getAllCustomerPaginationEntity(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<CustomerEntity>  customerEntity= iCustomerRepository.findAll(pageable);
        Page<CustomerDto> dto= null;
        return dto;
    }

    @Override
    public Page<CustomerDto> getAllCustomerPaginationEntityPageable(Pageable pageable, CustomerDto customerDto) {
        return null;
    }

} //end class
