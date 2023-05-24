package com.hamitmizrak.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

// LOMBOK
@Getter
@Setter

// Embeddable
@Embeddable
public class UserDetailsEmbeddable {

    // USER DETAILS
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

} //end class UserDetailsEmbeddable
