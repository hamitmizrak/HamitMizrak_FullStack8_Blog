package com.hamitmizrak.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
// @SneakyThrows

public class UserDetailBaseEntityDto {

    // UserDetails DEFAULT
    // Kullanıcı başlangıçta kilitli yani sisteme giremezsin
    @Builder.Default
    private Boolean isAccountNonLocked=false;

    // Kullanıcını Hesap süresi Doldu mu ?
    @Builder.Default
    private Boolean isAccountNonExpired=true;

    // Kulllanıcının Bilgi Süresi Doldu mu?
    @Builder.Default
    private Boolean isCredentialsNonExpired=true;

    // Kullanıcı Aktif mi ? pasif mi
    @Builder.Default
    private Boolean isEnabled=false;
}
