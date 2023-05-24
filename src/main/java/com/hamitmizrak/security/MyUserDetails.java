package com.hamitmizrak.security;

import com.hamitmizrak.data.entity.RoleEntity;
import com.hamitmizrak.data.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MyUserDetails implements UserDetails {

	// UserEntity
	private UserEntity userEntity;

	// Parametreli Constructor
	public MyUserDetails(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	// Kullanıcı izinlerini
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		// ROLE
		Set<RoleEntity> roles = userEntity.getRoles();

		// YETKI
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		// YETKILERI VER
		for (RoleEntity role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authorities;
	}

	// PASSWORD
	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}

	// USERNAME
	@Override
	public String getUsername() {
		return userEntity.getEmail();
	}

	// Kullanıcı Süresi
	@Override
	public boolean isAccountNonExpired() {
		return userEntity.getUserDetailsEmbeddable().getIsAccountNonExpired();
	}

	// Kullanıcı Kiliti
	@Override
	public boolean isAccountNonLocked() {
		return userEntity.getUserDetailsEmbeddable().getIsAccountNonLocked();
	}

	// Kimlik Bilgileri Kontol Eder
	@Override
	public boolean isCredentialsNonExpired() {
		return userEntity.getUserDetailsEmbeddable().getIsCredentialsNonExpired();
	}

	// Kullanıcı Aktif mi
	@Override
	public boolean isEnabled() {
		//@Embeddable aldım
		return userEntity.getUserDetailsEmbeddable().getIsEnabled();
	}

} //end class
