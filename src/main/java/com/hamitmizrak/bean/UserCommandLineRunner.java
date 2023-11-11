package com.hamitmizrak.bean;

import com.hamitmizrak.business.dto.BlogDto;
import com.hamitmizrak.business.dto.UserDto;
import com.hamitmizrak.business.services.IBlogGenericsServices;
import com.hamitmizrak.business.services.IUserService;
import com.hamitmizrak.data.entity.RoleEntity;
import com.hamitmizrak.data.repository.IRoleRepository;
import com.hamitmizrak.data.repository.IUserRepository;
import com.hamitmizrak.util.ERoles;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// LOMBOK
@RequiredArgsConstructor // Injection
@Log4j2

@Configuration
public class UserCommandLineRunner {

    private final IUserService iUserService;
    private final IUserRepository iUserRepository;
    private final IRoleRepository iRoleRepository;
    private final IBlogGenericsServices iBlogGenericsServices;

    // 1.YOL
    /**
     * @Bean CommandLineRunner createLogin(UserRegisterServiceImpl service){
     * //Lambda Expression Java=tineError Javascript=FateError
     * return (args)->{
     * UUID uuid=UUID.randomUUID();
     * UserRegisterDto userRegisterDto= UserRegisterDto.builder()
     * .email(uuid+"@gmail.com")
     * .password("root")
     * .username("hamit").build();
     * service.createUserRegister(userRegisterDto);
     * };
     */

    // proje ayağa kalkar kalmaz otomatik veri eklesin
    @Bean
    public CommandLineRunner userDataProcess() { // parametre olarak verebilirsin==> ICustomerServices customerServices
        return args -> {
            //Collection<RoleEntity> roles=(temp)->{}
            if (iUserRepository.findByEmail("hamitmizrak1@gmail.com").isPresent() == false) {
                //ROLES SUPER_ADMIN
                // Dikkat: ROLE_  eklemelisiiiin.
                Long superRoleId= iRoleRepository.save(RoleEntity.builder().rolesId(0L).roleName(ERoles.SuperAdmin.toString()).build()).getRolesId();
                Long adminRoleId= iRoleRepository.save(RoleEntity.builder().rolesId(0L).roleName(ERoles.ADMIN.toString()).build()).getRolesId();
                Long writerRoleId= iRoleRepository.save(RoleEntity.builder().rolesId(0L).roleName(ERoles.WRITER.toString()).build()).getRolesId();
                Long userRoleId= iRoleRepository.save(RoleEntity.builder().rolesId(0L).roleName(ERoles.USER.toString()).build()).getRolesId();

                for (long i = 1; i <=4; i++) {
                    //USER
                    UserDto userDto = new UserDto();
                    userDto.setName("Hamit"+i);
                    userDto.setSurname("Mızrak"+i);
                    userDto.setEmail("hamitmizrak"+i+"@gmail.com");
                    //userDto.setPassword(UUID.randomUUID().toString());
                    userDto.setPassword("Hm4444@.");

                    userDto.setIsAccountNonExpired(Boolean.TRUE);
                    userDto.setIsCredentialsNonExpired(Boolean.TRUE);
                    userDto.setIsAccountNonLocked(Boolean.TRUE);
                    userDto.setIsEnabled(Boolean.TRUE);
                    //KAYDET
                    iUserService.userCreate(i, userDto);
                    System.out.println(userDto);
                    System.out.println("User Eklendi");
                } //end for

                // User 2 tane eklemek
                //USER
                UserDto userDto = new UserDto();
                userDto.setName("Hamit"+5);
                userDto.setSurname("Mızrak"+5);
                userDto.setEmail("hamitmizrak"+5+"@gmail.com");
                //userDto.setPassword(UUID.randomUUID().toString());
                userDto.setPassword("Hm4444@.");

                userDto.setIsAccountNonExpired(Boolean.TRUE);
                userDto.setIsCredentialsNonExpired(Boolean.TRUE);
                userDto.setIsAccountNonLocked(Boolean.TRUE);
                userDto.setIsEnabled(Boolean.TRUE);
                //KAYDET  4: USER
                iUserService.userCreate(4L, userDto);
                System.out.println(userDto);
                System.out.println("User-2 Eklendi");

                //BLOG EKLE
                for (int i = 1; i <=10 ; i++) {
                    BlogDto blogDto= BlogDto.builder()
                            .header("Header "+i)
                            .content("Content "+i)
                            .image("Image "+i)
                            .build();
                    iBlogGenericsServices.blogServiceCreate(blogDto);
                } // end blog
            } //end if*/

           /* Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            if(authentication!=null && authentication.isAuthenticated()){
                System.out.println(authentication.getName());
                System.out.println(authentication.getPrincipal());
            }*/
        };
    } //end CommandLineRunner
} //end class
