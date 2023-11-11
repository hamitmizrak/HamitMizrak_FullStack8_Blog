package com.hamitmizrak.business.dto;

import com.hamitmizrak.annotation.UserUniqueEmail;
import com.hamitmizrak.audit.AuditingAwareBaseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.io.Serializable;
import java.util.Collection;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
// @SneakyThrows
public class UserDto extends AuditingAwareBaseDto implements Serializable {

    // SERILEŞTIRME
    public static final long serialVersionUID = 1L;

    //NAME
    @NotEmpty(message = "{user.name.validation.constraints.NotNull.message}")
    private String name;

    //SURNAME
    @NotEmpty(message = "{user.surname.validation.constraints.NotNull.message}")
    private String surname;

    //EMAIL
    @NotEmpty(message = "{user.email.validation.constraints.NotNull.message}")
    @Email(message = "{user.email.pattern.validation.constraints.NotNull.message}")
    @UserUniqueEmail // Kendi Annotation
    private String email;

    //PASSWORD
    //@JsonIgnore // backentte giden veriyi saklar
    @NotEmpty(message = "{user.password.validation.constraints.NotNull.NotNull.message}")
    @Size(min = 7, max = 15, message = "{user.password.pattern.validation.constraints.NotNull.message}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).*$", message = "{user.password.pattern.validation.constraints.NotNull.message}")
    private String password;

    //PAGE AUTHORIZATION (O Sayfaya yetkili Kişiler)
    @Builder.Default
    private boolean pageAuthorization = false;

    // ROLES (ENUM)
    // @Builder.Default // Default bileşen için kullanılır.
    // private ERoles userRoles=ERoles.USER;
    private Collection<RoleDto> roles;

    // #######################################################
    // USER DETAILS
    // Kullanıcı başlangıçta kilitli yani sisteme giremezsin(Mail ile aktif etsin)
    @Builder.Default
    private Boolean isAccountNonLocked = false;

    // Kullanıcını Hesap süresi Doldu mu ?
    @Builder.Default
    private Boolean isAccountNonExpired = true;

    // Kulllanıcının Bilgi Süresi Doldu mu?
    @Builder.Default
    private Boolean isCredentialsNonExpired = true;

    // Kullanıcı Aktif mi ? pasif mi
    @Builder.Default
    private Boolean isEnabled = true;

} //end class
