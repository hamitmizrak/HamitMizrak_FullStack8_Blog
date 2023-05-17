package com.hamitmizrak.user;

import com.hamitmizrak.data.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetail implements UserDetails {

    private String name;
    private String email;
    private String password;
    private List<GrantedAuthority> roles;

    // user details için ekledim
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;


    public CustomUserDetail(UserEntity userEntity){
        this.name=userEntity.getName();
        this.email=userEntity.getEmail();
        this.password=userEntity.getPassword();
        this.roles=userEntity.getRoles().stream().map((role -> new SimpleGrantedAuthority(role.getRoleName()))).collect(Collectors.toList());
        this.isAccountNonExpired=userEntity.isAccountNonExpired();
        this.isAccountNonLocked=userEntity.isAccountNonLocked();
        this.isCredentialsNonExpired=userEntity.isCredentialsNonExpired();
        this.isEnabled=userEntity.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    // password
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    // hesap süresi dolmuş mu
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    // hesap kilitli mi
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    // Kimlik bilgileri dolmuşmu
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    // Aktif mi
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}

