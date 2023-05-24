package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.business.dto.BlogDto;
import com.hamitmizrak.business.services.IBlogGenericsServices;
import com.hamitmizrak.data.entity.BlogEntity;
import com.hamitmizrak.data.repository.IBlogRepository;
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

@Transactional
@Service // Asıl iş yükünü yapan class
public class BlogServicesImpl implements IBlogGenericsServices<BlogDto, BlogEntity> {

    // INJECTION
    private final IBlogRepository iBlogRepository;
    private final ModelMapperBean modelMapperBean;
    private final IChooiseProfile profile;

    ////////////////////////////////////////
    // getProfile
    @Override
    public String profileService(String name) {
        log.info(profile.message(name));
        return profile.message(name);
    }

    // getAllHeaderData
    @Override
    public void headerService(Map<String, String> headers) {
        headers.forEach((key, value) -> {
            System.out.println("HeaderName: " + key + " HeaderValue: " + value);
        });
    }

    //App Information
    @Override
    public String appInformationService(HttpServletRequest request, HttpServletResponse response) {
        String URI = request.getRequestURI();
        String LOCALHOST = request.getLocalAddr();
        String SESSION = request.getSession().toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("uri: " + URI).append("<br/>localhost: " + LOCALHOST).append("<br/>session: " + SESSION);
        String informationToString = stringBuilder.toString();
        return informationToString;
    }

    ////////////////////////////////////////
    // SPEED DATA
    @Override
    public String speedDataService() {
        BlogDto blogDto = null;
        int counter = 1;
        for (int i = 1; i <= 5; i++) {
            blogDto = BlogDto.builder()
                    .header("header başlık buraya yazdım " + i)
                    .content("content başlık buraya yazdım " + i)
                    .image("image yazılmadı")
                    .build();
            counter++;
            // DTO ==> ENTITY Model Mapper
            BlogEntity blogEntity = DtoToEntity(blogDto);
            BlogEntity blogSave = iBlogRepository.save(blogEntity);
        }
        return counter + " tane veri eklendi";
    }

    // DELETE ALL
    @Override
    public String allDeleteService() {
        iBlogRepository.deleteAll();
        return "Bütün veriler silindi";
    }

    ////////////////////////////////////////
    //MODEL MAPPER
    @Override
    public BlogDto EntityToDto(BlogEntity blogEntity) {
        return modelMapperBean.modelMapperMethod().map(blogEntity, BlogDto.class);
    }

    @Override
    public BlogEntity DtoToEntity(BlogDto blogDto) {
        return modelMapperBean.modelMapperMethod().map(blogDto, BlogEntity.class);
    }

    ////////////////////////////////////////
    // CREATE
    @Override
    @Transactional // create,delete,update
    public BlogDto blogServiceCreate(BlogDto blogDto) {
        if (blogDto != null) {
            // DTO ==> ENTITY Model Mapper
            BlogEntity blogModel = DtoToEntity(blogDto);
            BlogEntity blogEntity = iBlogRepository.save(blogModel);
            // Kaydedilen verinin ID dönsün
            blogDto.setId(blogEntity.getId());
            blogDto.setCreatedDate(blogEntity.getCreatedDate());
        } else if (blogDto == null) {
            throw new HamitMizrakException("Blog Dto Null geldi");
        }
        return blogDto;
    }

    // LIST
    @Override
    public List<BlogDto> blogServiceList() {
        Iterable<BlogEntity> blogEntityList = iBlogRepository.findAll();
        // EntityList ==> DtoList çevir
        List<BlogDto> entityToDtoList = new ArrayList<>();
        for (BlogEntity temp : blogEntityList) {
            BlogDto blogDto = EntityToDto(temp);
            //byte[] decodeAccessKeyPin = Base64.getDecoder().decode(passwordEncoderBean.passwordEncoderMethod().encode(blogDto.getPassword()));
            //String decodeString = new String(decodeAccessKeyPin);
            //blogDto.setPassword(decodeString);
            entityToDtoList.add(blogDto);
        }
        return entityToDtoList;
    }

    // FIND
    @Override
    public BlogDto blogServiceFindById(Long id) {
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

        // 2.YOL
        BlogEntity blogEntity = null;
        if (id != null) {
            blogEntity = iBlogRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id + " nolu ID yoktur"));
        } else if (id == null) {
            throw new HamitMizrakException("Blog Dto Null geldi");
        }
        return EntityToDto(blogEntity);
    }

    // DELETE
    @Override
    @Transactional // create,delete,update
    public Map<String, Boolean> blogServiceDeleteId(Long id) {
        Map<String, Boolean> mapDelete = new HashMap<>();
        // FIND
        BlogDto blogDto = blogServiceFindById(id);
        // Dto ==> Entity
        BlogEntity blogModelEntity = DtoToEntity(blogDto);
        if (blogModelEntity != null) {
            iBlogRepository.delete(blogModelEntity);
            mapDelete.put("Silindi", Boolean.TRUE);
        }
        return mapDelete;
    }

    // UPDATE
    @Override
    @Transactional // create,delete,update
    public BlogDto blogServiceUpdateId(Long id, BlogDto blogDto) {
        // FIND
        BlogDto blogDtoFind = blogServiceFindById(id);
        // Dto ==> Entity
        BlogEntity blogEntity = DtoToEntity(blogDtoFind);
        if (blogEntity != null) {
            blogEntity.setHeader(blogDto.getHeader());
            blogEntity.setContent(blogDto.getContent());
            blogEntity.setImage(blogDto.getImage());
            iBlogRepository.save(blogEntity);
            // Kaydedilen verinin ID dönsün
            blogDto.setId(blogEntity.getId());
            blogDto.setCreatedDate(blogEntity.getCreatedDate());
        }
        return blogDto;
    }


    ////////////////////////////////////////
    // LIST : Pageable
    @Override
    public List<BlogDto> blogServiceAllList() {
        Iterable<BlogEntity> pageList = iBlogRepository.findAll();
        // EntityList ==> DtoList çevir
        List<BlogDto> entityToDtoList = new ArrayList<>();
        for (BlogEntity temp : pageList) {
            BlogDto blogDto = EntityToDto(temp);
            //byte[] decodeAccessKeyPin = Base64.getDecoder().decode(passwordEncoderBean.passwordEncoderMethod().encode(blogDto.getPassword()));
            //String decodeString = new String(decodeAccessKeyPin);
            //blogDto.setPassword(decodeString);
            entityToDtoList.add(blogDto);
        }
        return entityToDtoList;
    }

    // LIST PAGINATION Entity
    @Override
    public Page<BlogEntity> blogServicePagination(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<BlogEntity> blogRepositoryAll = iBlogRepository.findAll(pageable);
        return blogRepositoryAll;
    }


    /////////////////////
    // LIST PAGINATION Entity Pageable Object
    @Override
    public Page<BlogDto> blogServicePageable(Pageable pageable,  BlogDto registerDto) {
        return null;
    }
   /* @Override
    //Sistemdeki kullanıcı bilgisini almak:  @UserLoginSystem
    public Page<BlogDto> blogServicePageable(Pageable pageable, @UserLoginSystem BlogDto registerDto) {
        List<RegisterDto> dtoList = new ArrayList<>();
        if (registerDto.getEmail() != null) {
            List<RegisterEntity> registerEntity = iUserRegisterRepository.findByEmailNot(registerDto.getEmail());
            System.out.println(registerEntity);
        }
        //Pageable
        // 1.YOL ModelMapper Entity ==> DTO çevirmek
        // NOT: Pageable dönüştürmek
        // 2.YOL Page.map() Entity ==> DTO çevirmek
        // istersen Lambda Function yapabilirsin.
        // istersen Method Referances yapabilirsin.
        return iUserRegisterRepository.findAll(pageable).map(new Function<RegisterEntity, RegisterDto>() {
          @Override
          // Entity Dto Çevirmek  (Model Mapper kulalnabilirsin)
           public BlogDto apply (RegisterEntity registerEntity){
            RegisterDto registerDto = new RegisterDto();
            registerDto.setImage(registerEntity.getImage());
            registerDto.setCheck(registerEntity.getCheck());
            registerDto.setEmail(registerEntity.getEmail());
            registerDto.setId(registerEntity.getId());
            registerDto.setUname(registerEntity.getUname());
            registerDto.setPasswd(registerEntity.getPasswd());
            registerDto.setCreatedDate(registerEntity.getCreatedDate());
            dtoList.add(registerDto);
            return registerDto;
        }
    });
   } //end LIST PAGINATION*/

} //end class
