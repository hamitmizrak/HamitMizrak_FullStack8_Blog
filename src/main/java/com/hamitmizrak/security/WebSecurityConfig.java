package com.hamitmizrak.security;

import com.hamitmizrak.bean.PasswordEncoderBean;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// LOMBOK
@RequiredArgsConstructor

// SECURITY
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    // INJECTION
    private final PasswordEncoderBean passwordEncoderBean;

    // Application Properties
    @Value("${spring.security.user.name}")
    private String user;

    @Value("${spring.security.user.password}")
    private String password;

    @Value("${spring.security.user.roles}")
    private String roles;

    @Bean
    @SneakyThrows // throws Exception
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        // http.httpBasic(); //http düzeyinde
        // http.formLogin(); // Form sayfası düzeyinde
        http.authorizeHttpRequests()
                .requestMatchers("/", "/index", "/login").permitAll() // index izin ver
                .requestMatchers("/email/api/v1/basic/email").permitAll() // Email Göndermeye izin ver
                .requestMatchers("/swagger-ui/**").permitAll() // Email Göndermeye izin ver
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();

        // H2-Console
        http.csrf().disable();

        // H2-Console içindeki tabları aktifleştirmek
        http.headers().frameOptions().disable();

        return http.build();
    } //end securityFilterChain

    // Kullanıcı Eklemek
    @Autowired
    @SneakyThrows //throws Exception
    public void myUserAddUserRoles(AuthenticationManagerBuilder auth) {
        //inMemoryAuthentication() = database olmadan login
        // noEncrpted: yani maskesiz olarak sakla
        auth.inMemoryAuthentication()
                .withUser(this.user)
                .password(passwordEncoderBean.passwordEncoderMethod().encode(this.password))
                //.password("root")
                .roles(this.roles);
    }

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




