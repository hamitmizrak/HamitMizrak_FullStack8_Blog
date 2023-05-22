package com.hamitmizrak.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hamitmizrak.util.ERoles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

// LOMBOK
@RequiredArgsConstructor

@Component
public class UserDetailsEntity implements UserDetails {

    @Override
    //spring.jackson.mapper.default-view-inclusion=true
    @JsonIgnore // bu field json görünmesini sağlamamak için
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(ERoles.USER.toString());
    }

    @Override
    //spring.jackson.mapper.default-view-inclusion=true
    @JsonIgnore // bu field json görünmesini sağlamamak için
    public String getPassword() {
        return null;
    }

    @Override
    //spring.jackson.mapper.default-view-inclusion=true
    @JsonIgnore // bu field json görünmesini sağlamamak için
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // başlangıçta kullanıcıyı kilitli yapalım.
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
