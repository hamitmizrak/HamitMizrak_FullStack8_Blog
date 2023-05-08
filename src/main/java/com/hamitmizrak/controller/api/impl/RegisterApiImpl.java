package com.hamitmizrak.controller.api.impl;

import com.hamitmizrak.business.dto.RegisterDto;
import com.hamitmizrak.business.services.impl.RegisterServices;
import com.hamitmizrak.controller.api.IGenericsApi;
import com.hamitmizrak.error.ApiResult;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// LOMBOK
@Log4j2
@RequiredArgsConstructor // Injection

// Apı
@RestController
@CrossOrigin(origins = "http://localhost:3000")
// CORS: eğer reactta package.json'da proxy yazarsam @CrossOrigin yazmasamda olur.
@RequestMapping("/admin/api/v1")
public class RegisterApiImpl implements IGenericsApi<RegisterDto> {

    // ERROR
    private ApiResult apiResult;

    // @PostConstruct
    @PostConstruct
    public void springData() {
        apiResult = new ApiResult();
    }

    //Injection
    //1.YOL => Field Injection
    /*
    @Autowired
    private RegisterServices registerServices;
    */

    //2.YOL => Constructor Injection
    /*
    private final RegisterServices registerServices;
    @Autowired
    public CustomerApi(RegisterServices registerServices) {
        this.registerServices = registerServices;
    }
    */

    //3.YOL => Constructor Injection (LOMBOK)
    private final RegisterServices registerServices;

    ///////////////////////////////////////////////////////////

    //PROFILE
    @Override
    @GetMapping("/profile/{data}")
    public String getProfile(@PathVariable(name = "data") String name) {
        //log.info();
        return registerServices.getProfile(name);
    }

    //HEADERS
    @Override
    @GetMapping("/header")
    public void getAllHeaderData(Map<String, String> headers) {
        registerServices.getAllHeaderData(headers);
    }

    // APP INFORMATION
    @Override
    @GetMapping("/app/information")
    public ResponseEntity<?> getAppInformation(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(registerServices.getAppInformation(request,response));
    }
    //////////////////////////////////////////////////////////

    // SPEED ALL
    // http://localhost:2222/admin/api/v1/speedData
    @Override
    @GetMapping("/speedData")
    public ResponseEntity<RegisterDto> speedDataApi() {
        return ResponseEntity.ok(registerServices.speedDataServices());
    }

    // DELETE ALL
    // http://localhost:2222/admin/api/v1/deleteall
    @Override
    @GetMapping("/deleteall")
    public ResponseEntity<String> deleteApi() {
        return ResponseEntity.ok(registerServices.allDeleteServices());
    }
    ////////////////////////////////////////////////////////////////////

    // CREATE
    // http://localhost:2222/admin/api/v1/create
    @Override
    @PostMapping(value = "create")
    public ResponseEntity<RegisterDto> createApi(@Valid @RequestBody RegisterDto registerDto) {
        // return new ResponseEntity<>(registerDto, HttpStatus.OK);
        // return  ResponseEntity.status(HttpStatus.OK).body(registerDto);
        // return  ResponseEntity.status(200).body(registerDto);
        // return  ResponseEntity.ok().body(registerDto);

        // ApiResult apiResult=new ApiResult(200,PATH,"created Employee");
        // return ResponseEntity.ok(apiResult);
        return ResponseEntity.ok(registerServices.createServices(registerDto));
    }

    // LIST
    // http://localhost:2222/admin/api/v1/list
    @Override
    @GetMapping("list")
    @Cacheable(value = "cacheAdminList")
    public ResponseEntity<List<RegisterDto>> listApi() {
        return ResponseEntity.ok(registerServices.listServices());
    }

    // FIND
    // http://localhost:2222/admin/api/v1/find
    // http://localhost:2222/admin/api/v1/find/0
    // http://localhost:2222/admin/api/v1/find/-1
    // http://localhost:2222/admin/api/v1/find/1
    @Override
    @GetMapping({"/find", "/find/{id}"})
    public ResponseEntity<?> findByIdApi(@PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            log.error("API => 404 NOT FOUND");
            //return ResponseEntity.notFound().build();
        } else if (id == 0) {
            log.error("API => 400 BAD REQUEST");
            apiResult = new ApiResult(400, "localhost:2222/customer/api/v1/register/0", "Kötü istek", "Bad Request");
            //return ResponseEntity.badRequest().build();
            return ResponseEntity.badRequest().body(apiResult);
        } else if (id < 0) {
            log.error("API => 401 UNAUTHROZED");
            apiResult = ApiResult.builder()
                    .error("unAuthorized")
                    .message("Yetkisiz Giriş")
                    .path("localhost:2222/customer/api/v1/register/-1")
                    .status(401)
                    .build();
            return ResponseEntity.status(401).body(apiResult);
        }
        System.out.println(registerServices.findByIdServices(id));
        return ResponseEntity.ok(registerServices.findByIdServices(id));
    }

    // DELETE
    // http://localhost:2222/admin/api/v1/delete/1
    @Override
    @DeleteMapping({"/delete", "/delete/{id}"})
    public ResponseEntity<Map<String, Boolean>> deleteApi(@PathVariable(name = "id", required = false) Long id) {
        return ResponseEntity.ok(registerServices.deleteServices(id));
    }

    // UPDATE
    // http://localhost:2222/admin/api/v1/update/1
    @Override
    @PutMapping({"/update", "/update/{id}"})
    public ResponseEntity<RegisterDto> updateApi(@PathVariable(name = "id", required = false) Long id, @Valid @RequestBody RegisterDto adminDto) {
        return ResponseEntity.ok(registerServices.updateServices(id, adminDto));
    }
} // end update
