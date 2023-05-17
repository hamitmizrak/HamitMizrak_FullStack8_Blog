package com.hamitmizrak.controller.api.impl;

import com.hamitmizrak.business.dto.CustomerDto;
import com.hamitmizrak.business.services.impl.CustomerServicesImpl;
import com.hamitmizrak.controller.api.IGenericsApi;
import com.hamitmizrak.error.ApiResult;
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
@CrossOrigin(origins = "http://localhost:3000")
// CORS: eğer reactta package.json'da proxy yazarsam @CrossOrigin yazmasamda olur.
@RequestMapping("/customer/api/v1")
public class CustomerApiImpl implements IGenericsApi<CustomerDto> {

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
    private CustomerServices customerServices;
    */

    //2.YOL => Constructor Injection
    /*
    private final CustomerServices customerServices;
    @Autowired
    public CustomerApi(CustomerServices customerServices) {
        this.customerServices = customerServices;
    }
    */

    //3.YOL => Constructor Injection (LOMBOK)
    private final CustomerServicesImpl customerServices;

    ///////////////////////////////////////////////////////////

    //PROFILE
    @Override
    @GetMapping("/profile/{data}")
    public String getProfile(@PathVariable(name = "data") String name) {
        //log.info();
        return customerServices.getProfile(name);
    }

    //HEADERS
    @Override
    @GetMapping("/header")
    public void getAllHeaderData(Map<String, String> headers) {
        customerServices.getAllHeaderData(headers);
    }

    // APP INFORMATION
    @Override
    @GetMapping("/app/information")
    public ResponseEntity<?> getAppInformation(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(customerServices.getAppInformation(request,response));
    }
    //////////////////////////////////////////////////////////

    // SPEED ALL
    // http://localhost:2222/customer/api/v1/speedData
    @Override
    @GetMapping("/speedData")
    public ResponseEntity<CustomerDto> speedDataApi() {
        return ResponseEntity.ok(customerServices.speedDataServices());
    }

    // DELETE ALL
    // http://localhost:2222/customer/api/v1/deleteall
    @Override
    @GetMapping("/deleteall")
    public ResponseEntity<String> deleteApi() {
        return ResponseEntity.ok(customerServices.allDeleteServices());
    }
    ////////////////////////////////////////////////////////////////////

    // CREATE
    // http://localhost:2222/customer/api/v1/create
    @Override
    @PostMapping(value = "create")
    public ResponseEntity<CustomerDto> createApi(@Valid @RequestBody CustomerDto customerDto) {
        // return new ResponseEntity<>(customerDto, HttpStatus.OK);
        // return  ResponseEntity.status(HttpStatus.OK).body(customerDto);
        // return  ResponseEntity.status(200).body(customerDto);
        // return  ResponseEntity.ok().body(customerDto);

        // ApiResult apiResult=new ApiResult(200,PATH,"created Employee");
        // return ResponseEntity.ok(apiResult);
        return ResponseEntity.ok(customerServices.createServices(customerDto));
    }

    // LIST
    // http://localhost:2222/customer/api/v1/list
    @Override
    @GetMapping("list")
    // @Cacheable(value = "cachecustomerList")  // Dikkat: bunu eklediğinde veriler değişikler göreyebilirsin
    public ResponseEntity<List<CustomerDto>> listApi() {
        return ResponseEntity.ok(customerServices.listServices());
    }

    // FIND
    // http://localhost:2222/customer/api/v1/find
    // http://localhost:2222/customer/api/v1/find/0
    // http://localhost:2222/customer/api/v1/find/-1
    // http://localhost:2222/customer/api/v1/find/1
    @Override
    @GetMapping({"/find", "/find/{id}"})
    public ResponseEntity<?> findByIdApi(@PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            log.error("API => 404 NOT FOUND");
            //return ResponseEntity.notFound().build();
        } else if (id == 0) {
            log.error("API => 400 BAD REQUEST");
            apiResult = new ApiResult(400, "localhost:2222/customer/api/v1/customer/0", "Kötü istek", "Bad Request");
            //return ResponseEntity.badRequest().build();
            return ResponseEntity.badRequest().body(apiResult);
        } else if (id < 0) {
            log.error("API => 401 UNAUTHROZED");
            apiResult = ApiResult.builder()
                    .error("unAuthorized")
                    .message("Yetkisiz Giriş")
                    .path("localhost:2222/customer/api/v1/customer/-1")
                    .status(401)
                    .build();
            return ResponseEntity.status(401).body(apiResult);
        }
        System.out.println(customerServices.findByIdServices(id));
        return ResponseEntity.ok(customerServices.findByIdServices(id));
    }

    // DELETE
    // http://localhost:2222/customer/api/v1/delete/1
    @Override
    @DeleteMapping({"/delete", "/delete/{id}"})
    public ResponseEntity<Map<String, Boolean>> deleteApi(@PathVariable(name = "id", required = false) Long id) {
        return ResponseEntity.ok(customerServices.deleteServices(id));
    }

    // UPDATE
    // http://localhost:2222/customer/api/v1/update/1
    @Override
    @PutMapping({"/update", "/update/{id}"})
    public ResponseEntity<CustomerDto> updateApi(@PathVariable(name = "id", required = false) Long id, @Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerServices.updateServices(id, customerDto));
    }
    ///////////////////////////////////////////////////////////////////////////////////

    //ROOT
    @GetMapping({"/","/index"})
    public ResponseEntity<String> getRoot() {
        return ResponseEntity.ok("index");
    }

    // LIST PAGINATION
    // http://localhost:4444/api/v1/pagination?currentPage=0&pageSize=3
    @Override
    @GetMapping(value = "/pagination")
    public ResponseEntity<Page<CustomerDto>> getAllCustomerPaginationEntity(
            @RequestParam(name = "currentPage", required = false, defaultValue = "0") int currentPage,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize) {
        return ResponseEntity.ok(customerServices.getAllCustomerPaginationEntity(currentPage,pageSize));
    }

    @Override
    public ResponseEntity<Page<CustomerDto>> getAllCustomerPaginationEntityPageable(Pageable pageable, CustomerDto customerDto) {
        return null;
    }

} // end update
