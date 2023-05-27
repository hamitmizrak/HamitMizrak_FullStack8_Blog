package com.hamitmizrak.security;

import com.hamitmizrak.util.ERoles;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// LOMBOK
@RequiredArgsConstructor

// SECURITY
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    // Application Properties
    @Value("${spring.security.user.name}")
    private String user;

    @Value("${spring.security.user.password}")
    private String password;

    @Value("${spring.security.user.roles}")
    private String roles;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Autowired
    @SneakyThrows //throws Exception
    public void myDatabaseAddUserRoles(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    // 1 SuperAdmin hamitmizrak1@gmail.com Hm4444@.
    // 2 Admin      hamitmizrak2@gmail.com Hm4444@.
    // 3 Writer     hamitmizrak3@gmail.com Hm4444@.
    // 4 User       hamitmizrak4@gmail.com Hm4444@.
    @Bean
    @SneakyThrows // throws Exception
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        // DİKKAT:  Postman kullanmak için httpBasic() yapmalısın.
        // http.httpBasic(); //http düzeyinde
        // http.formLogin(); // Form sayfası düzeyinde
        // ROLE_ başına eklemek veya eklemmek duruma göre değişiyor.
        http.authorizeRequests()

                .requestMatchers("/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/", "/index").permitAll() // index izin ver
                .requestMatchers("/login", "/logout").permitAll() // login/logout izin ver
                .requestMatchers("/email/api/v1/basic/email").permitAll() // Email Göndermeye izin ver
                .requestMatchers("/user/api/v1/confirm/**").permitAll() // Email Confirim izin ver
                .requestMatchers("/login/api/v1/authentication").permitAll() // Login authentication
                .requestMatchers("/user/api/v1/create/**").permitAll() // Register izin vermek
                .requestMatchers("/roles/api/v1/roles/**").permitAll() // Role Sorgulaması izin vermek



                .requestMatchers("/swagger-ui/**").hasAnyAuthority(ERoles.ADMIN.toString(), ERoles.SuperAdmin.toString())  // swagger-ui
                .requestMatchers("/h2-console/**").hasAnyAuthority(ERoles.ADMIN.toString(), ERoles.SuperAdmin.toString())  //h2-console

                //.requestMatchers("/").hasAnyAuthority("USER", "CREATOR", "EDITOR", "ADMIN")
                .requestMatchers("/blog/api/v1/list").hasAnyAuthority(ERoles.WRITER.toString(), ERoles.ADMIN.toString(), ERoles.SuperAdmin.toString()) //admin admin
                .requestMatchers("/blog/api/v1/create ").hasAnyAuthority(ERoles.WRITER.toString(), ERoles.ADMIN.toString(), ERoles.SuperAdmin.toString()) //admin admin
                .requestMatchers("/blog/**").hasAnyAuthority(ERoles.WRITER.toString(), ERoles.ADMIN.toString(), ERoles.SuperAdmin.toString()) // Blog izin ver

                //.requestMatchers("/user/**").hasAnyRole("ADMIN","SUPER_ADMIN")
                .requestMatchers("/user/api/v1/roles").hasAnyAuthority(ERoles.WRITER.toString(), ERoles.ADMIN.toString(), ERoles.SuperAdmin.toString())
                //.requestMatchers("/user/**").hasAnyRole("ADMIN","SUPER_ADMIN")
                .requestMatchers("/api/**").hasAnyAuthority(ERoles.WRITER.toString(), ERoles.ADMIN.toString(), ERoles.SuperAdmin.toString())

                .anyRequest()
                .authenticated()
                .and()
                //.httpBasic(); // Postman çalışması için açmalısın
                .formLogin();//Eğer React ile kullanmak istiyorsan bunu açmalısın
               /* .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");*/

                /*
                 //.loginPage("/login") //LOGIN
                        //.defaultSuccessUrl("/login")
                        //.and()
                        //.logout()
                        //.logoutSuccessUrl("/logout") //LOGOUT
                        //.invalidateHttpSession(true)// var oaln session silmek
                        //.permitAll();
                */
        // H2-Console
        http.csrf().disable();

        // H2-Console içindeki tabları aktifleştirmek
        http.headers().frameOptions().disable();

        return http.build();
    } //end securityFilterChain


    // DATABASE ÜZERİNDE OLMADAN (1.YOL)
    // inMemoryAuthentication(Database Eklemedene Kullanıcı Eklemek)
    // 1.YOL
    // DATABASE ÜZERİNDE OLMADAN
    // inMemoryAuthentication(Database Eklemedene Kullanıcı Eklemek)
    // UNUTMA: ROLLER BÜYÜK HARFLE YAZILIR.
    // inMemoryAuthentication() = database olmadan login
    // noEncrpted: yani maskesiz olarak sakla
    // SUPER ADMIN
    // Kullanıcı Eklemek
  /*  @Autowired
    @SneakyThrows //throws Exception
    public void myUserAddUserRoles(AuthenticationManagerBuilder auth) {
        auth.inMemoryAuthentication()
                .withUser(this.user) // hamitmizrak@gmail.com
                .password(passwordEncoderBean.passwordEncoderMethod().encode(this.password))// Hm123456@.
                .roles(this.roles);

        auth.inMemoryAuthentication()
                .withUser("admin") //
                .password(passwordEncoderBean.passwordEncoderMethod().encode("root"))//
                .roles("ADMIN");

        // NORMAL USER
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoderBean.passwordEncoderMethod().encode("root"))
                .roles("USER");
    }*/


    // DATABASE ÜZERİNDE OLMADAN (2.YOL)
    /**    @Bean public UserDetailsService users() {
    // The builder will ensure the passwords are encoded before saving in memory
    User.UserBuilder users = User.withDefaultPasswordEncoder();
    UserDetails user = users
    .username("user")
    .password("password")
    .roles("USER")
    .build();
    UserDetails admin = users
    .username("admin")
    .password("password")
    .roles("USER", "ADMIN")
    .build();
    return new InMemoryUserDetailsManager(user, admin);
    }*/

    // 1. YOL Şifre Maskelensin
     /*
     @Bean
     public PasswordEncoder passwordEncoder() {
     PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
          return passwordEncoder;
     }
     */

    // 2.YOL Şifre maskelenmesin
    //Deprecated olan bir metot ve artık kullanmamıza gerek yok noop derken yetiyor.
   /*
   @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    */



} //end class

/**
 * SELECT * FROM sales3.users;
 * SELECT * FROM sales3.roles;
 * SELECT * FROM sales3.users_roles;
 * <p>
 * INSERT INTO `sales3`.`users` (`email`, `account_expired`, `locked`, `credentials_expired`, `enabled`, `page_authorization`, `password`, `name`, `surname`) VALUES ('admin', 1, 1, 1, 1, 1, '$2a$10$coGsqvvKLmvNWj79PUqklu/yA/hRFWDABppB4NuGBsI1oqg.tXHhO', 'admin', 'admin');
 * INSERT INTO `sales3`.`users` (`email`, `account_expired`, `locked`, `credentials_expired`, `enabled`, `page_authorization`, `password`, `name`, `surname`) VALUES ('user', 1, 1, 1, 1, 1, '$2a$10$coGsqvvKLmvNWj79PUqklu/yA/hRFWDABppB4NuGBsI1oqg.tXHhO', 'admin', 'admin');
 * <p>
 * <p>
 * INSERT INTO `sales3`.`users_roles` (`user_id`, `role_id`) VALUES ('2', '2');
 * INSERT INTO `sales3`.`users_roles` (`user_id`, `role_id`) VALUES ('3', '3');
 * <p>
 * super Hm4444@.
 * admin Hm4444@.
 * user  Hm4444@.
 * hamitmizrak@gmail.com Hm4444@.
 */




