package com.hamitmizrak.data.entity;

import com.hamitmizrak.util.ERoles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

// LOMBOK
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
// @SneakyThrows

@Entity
@Table(name="blog_user")
// 1(UserEntity)- 1(TokenCEntity) -
public class UserEntity extends BaseEntity implements UserDetails {

    private String name;
    private String surname;
    private String email;
    private String password;

    // ROLES
    @Column(name = "roles_name")
    private ERoles userRoles;

    // UserDetails DEFAULT
    // Kullanıcı başlangıçta kilitli yani sisteme giremezsin
    @Column(name = "locked")
    private Boolean isAccountNonLocked;

    // Kullanıcını Hesap süresi Doldu mu ?
    @Column(name = "account_expired")
    private Boolean isAccountNonExpired;

    // Kulllanıcının Bilgi Süresi Doldu mu?
    @Column(name = "credentials_expired")
    private Boolean isCredentialsNonExpired;

    // Kullanıcı Aktif mi ? pasif mi
    @Column(name = "enabled")
    private Boolean isEnabled;


    // UserDetails (implements)
    // ROLES
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final SimpleGrantedAuthority simpleGrantedAuthority= new SimpleGrantedAuthority(userRoles.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    //PASSWORD
    @Override
    public String getPassword() {
        return this.password;
    }

    //USERNAME
    @Override
    public String getUsername() {
        return this.email;
    }

    //Kullanıcını Hesap süresi Doldu mu ?
    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    // Kullanıcı Hesabı kilitli mi ?
    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    // Kulllanıcının Bilgi Süresi Doldu mu?
    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    // Kullanıcı Aktif mi, Pasif mi ?(kullanıcı Sistemdeki)
    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
