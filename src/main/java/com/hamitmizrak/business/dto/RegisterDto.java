package com.hamitmizrak.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hamitmizrak.annotation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2

// Validation
public class RegisterDto extends AuditingAwareBaseDto implements Serializable {

    //Serileştirme
    public static final Long serialVersionUID = 1L;

    //NAME
    @NotEmpty(message = "{admin.name.validation.constraints.NotNull.message}")
    private String name;

    //SURNAME
    @NotEmpty(message = "{admin.surname.validation.constraints.NotNull.message}")
    private String surname;

    //EMAİL
    @NotEmpty(message = "{admin.email.validation.constraints.NotNull.message}")
    @Email(message = "{admin.email.pattern.validation.constraints.NotNull.message}")
    @Size(max = 200)
    //Kendi Anontation yazdım
    @UniqueEmail(message = "{admin.email.unique.validation.constraints.NotNull.message}")
    private String email;

    //PASSWORD
    //@Min(value = 7,message = "Şifreyi 7 küçük giremezsiniz")
    //@Max(value = 7,message = "Şifreyi 10 büyük giremezsiniz")
    @NotEmpty(message = "{admin.password.validation.constraints.NotNull.NotNull.message}")
    @Size(min = 7, max = 10, message = "{admin.password.pattern.validation.constraints.NotNull.message}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).*$", message = "{admin.password.pattern.validation.constraints.NotNull.message}")
    @JsonIgnore // bu field json görünmesini istemediğimiz Entity yazarı json görünmez.
    private String password;

    // other info
    private String image;
    private Object specialObject;

    //UserDetails için
    private Collection<? extends GrantedAuthority> getAuthorities;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

} // end class