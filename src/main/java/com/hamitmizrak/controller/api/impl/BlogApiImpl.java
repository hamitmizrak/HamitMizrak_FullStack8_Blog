package com.hamitmizrak.controller.api.impl;

import com.hamitmizrak.business.dto.BlogDto;
import com.hamitmizrak.business.services.impl.BlogServicesImpl;
import com.hamitmizrak.controller.api.IBlogGenericsApi;
import com.hamitmizrak.data.entity.BlogEntity;
import com.hamitmizrak.error.ApiResult;
import com.hamitmizrak.util.FrontendURL;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// LOMBOK
@Log4j2
@RequiredArgsConstructor // Injection

// Apı
@RestController
// CORS: eğer reactta package.json'da proxy yazarsam @CrossOrigin yazmasamda olur.
@CrossOrigin(origins = FrontendURL.FRONTEND_URL)// @CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/blog/api/v1")
public class BlogApiImpl implements IBlogGenericsApi<BlogDto> {

    //Injection
    //1.YOL => Field Injection
    /*
    @Autowired
    private BlogServices blogServices;
    */

    //2.YOL => Constructor Injection
    /*
    private final BlogServices blogServices;
    @Autowired
    public BlogApi(BlogServices blogServices) {
        this.blogServices = blogServices;
    }
    */

    //3.YOL => Constructor Injection (LOMBOK)
    private final BlogServicesImpl blogServices;

    ///////////////////////////////////////////////////////////
    // ERROR
    private ApiResult apiResult;

    // @PostConstruct
    @PostConstruct
    public void springData() {
        apiResult = new ApiResult();
    }

    ///////////////////////////////////////////////////////////

    // ROOT
    // http://localhost:2222/blog/api/v1
    // http://localhost:2222/blog/api/v1/index
    @GetMapping({"/","/index"})
    public ResponseEntity<String> getRoot() {
        return ResponseEntity.ok("index");
    }

    // PROFILE
    // http://localhost:2222/blog/api/v1/profile/frontend
    @Override
    @GetMapping("/profile/{data}")
    public ResponseEntity<String>  profileApi(@PathVariable(name = "data") String name) {
        //log.info();
        return ResponseEntity.ok(blogServices.profileService(name)) ;
    }

    // HEADERS
    // http://localhost:2222/blog/api/v1/header
    @Override
    @GetMapping("/header")
    public ResponseEntity<?>  headerApi(Map<String, String> headers) {
        blogServices.headerService(headers);
        return ResponseEntity.ok("Header Data");
    }

    // APP INFORMATION
    // http://localhost:2222/blog/api/v1/app/information
    @Override
    @GetMapping("/app/information")
    public ResponseEntity<?> appInformationApi(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(blogServices.appInformationService(request,response));
    }
    //////////////////////////////////////////////////////////

    // SPEED ALL
    // http://localhost:2222/blog/api/v1/speedData
    @Override
    @GetMapping("/speedData")
    public ResponseEntity<String> speedDataApi() {
        return ResponseEntity.ok(blogServices.speedDataService());
    }

    // DELETE ALL
    // http://localhost:2222/blog/api/v1/deleteAll
    @Override
    @GetMapping("/deleteAll")
    public ResponseEntity<String> allDeleteApi() {
        return ResponseEntity.ok(blogServices.allDeleteService());
    }
    ////////////////////////////////////////////////////////////////////

    // CREATE
    // http://localhost:2222/blog/api/v1/create
    @Override
    @PostMapping(value = "create")
    public ResponseEntity<BlogDto> blogApiCreate(@Valid @RequestBody BlogDto blogDto) {
        // return new ResponseEntity<>(blogDto, HttpStatus.OK);
        // return  ResponseEntity.status(HttpStatus.OK).body(blogDto);
        // return  ResponseEntity.status(200).body(blogDto);
        // return  ResponseEntity.ok().body(blogDto);

        // ApiResult apiResult=new ApiResult(200,PATH,"created Employee");
        // return ResponseEntity.ok(apiResult);
        return ResponseEntity.ok(blogServices.blogServiceCreate(blogDto));
    }

    // LIST
    // http://localhost:2222/blog/api/v1/list
    @Override
    @GetMapping("list")
    // @Cacheable(value = "cacheBlogList")  // Dikkat: bunu eklediğinde veriler değişikler göreyebilirsin
    public ResponseEntity<List<BlogDto>> blogApiList() {
        return ResponseEntity.ok(blogServices.blogServiceList());
    }

    // FIND
    // http://localhost:2222/blog/api/v1/find
    // http://localhost:2222/blog/api/v1/find/0
    // http://localhost:2222/blog/api/v1/find/-1
    // http://localhost:2222/blog/api/v1/find/1
    @Override
    @GetMapping({"/find", "/find/{id}"})
    public ResponseEntity<?> blogApiFindById(@PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            log.error("API => 404 NOT FOUND");
            //return ResponseEntity.notFound().build();
        } else if (id == 0) {
            log.error("API => 400 BAD REQUEST");
            apiResult = new ApiResult(400, "localhost:2222/blog/api/v1/blog/0", "Kötü istek", "Bad Request");
            //return ResponseEntity.badRequest().build();
            return ResponseEntity.badRequest().body(apiResult);
        } else if (id < 0) {
            log.error("API => 401 UNAUTHROZED");
            apiResult = ApiResult.builder()
                    .error("unAuthorized")
                    .message("Yetkisiz Giriş")
                    .path("localhost:2222/blog/api/v1/blog/-1")
                    .status(401)
                    .build();
            return ResponseEntity.status(401).body(apiResult);
        }
        System.out.println(blogServices.blogServiceFindById(id));
        return ResponseEntity.ok(blogServices.blogServiceFindById(id));
    }

    // DELETE
    // http://localhost:2222/blog/api/v1/delete/1
    @Override
    @DeleteMapping({"/delete", "/delete/{id}"})
    public ResponseEntity<Map<String, Boolean>> blogApiDeleteId(@PathVariable(name = "id", required = false) Long id) {
        return ResponseEntity.ok(blogServices.blogServiceDeleteId(id));
    }

    // UPDATE
    // http://localhost:2222/blog/api/v1/update/1
    @Override
    @PutMapping({"/update", "/update/{id}"})
    public ResponseEntity<BlogDto> blogApiUpdateId(@PathVariable(name = "id", required = false) Long id, @Valid @RequestBody BlogDto blogDto) {
        return ResponseEntity.ok(blogServices.blogServiceUpdateId(id, blogDto));
    }
    ///////////////////////////////////////////////////////////////////////////////////

    // LIST PAGINATION
    // http://localhost:4444/api/v1/pagination?currentPage=0&pageSize=3
    @Override
    @GetMapping(value = "/pagination")
    public ResponseEntity<Page<BlogEntity>> blogApiPagination(
            @RequestParam(name = "currentPage", required = false, defaultValue = "0") int currentPage,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize) {
        return ResponseEntity.ok(blogServices.blogServicePagination(currentPage,pageSize));
    }

    // LIST PAGINATION
    // spring.data.web.pageable.page-parameter=currentPage
    // spring.data.web.pageable.size-parameter=pageSize
    // http://localhost:4444/api/v1/pageable?currentPage=0&pageSize=3
    // http://localhost:4444/api/v1/pageable?page=0&size=3
    @GetMapping(value = "/pageable")
    @Override
    // Sistemdeki kullanıcı bilgisini almak:  @UserLoginSystem
    // (Pageable pageable, @UserLoginSystem BlogDto blogDto
    public ResponseEntity<Page<BlogDto>> blogApiPageable(Pageable pageable,BlogDto blogDto) {
        return ResponseEntity.ok(blogServices.blogServicePageable(pageable, blogDto));
    }
} // end update
