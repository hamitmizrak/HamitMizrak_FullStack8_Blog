package com.hamitmizrak.business.dto;

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

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2

// Validation
public class AdminDto extends AuditingAwareBaseDto implements Serializable {
    //Serileştirme
    public static final Long serialVersionUID = 1L;

    //NAME
    @NotEmpty(message = "{customer.name.validation.constraints.NotNull.message}")
    private String name;

    //SURNAME
    @NotEmpty(message = "{customer.surname.validation.constraints.NotNull.message}")
    private String surname;

    //EMAİL
    @NotEmpty(message = "{customer.email.validation.constraints.NotNull.message}")
    @Email(message = "{customer.email.validation.regex.constraints.NotNull.message}")
    private String email;

    //PASSWORD
    //@Min(value = 7,message = "Şifreyi 7 küçük giremezsiniz")
    //@Max(value = 7,message = "Şifreyi 10 büyük giremezsiniz")
    @NotEmpty(message = "{customer.password.validation.constraints.NotNull.message}")
    @Size(min = 7, max = 10, message = "{customer.password.pattern.validation.constraints.NotNull.message}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).*$", message = "{customer.password.pattern.validation.constraints.NotNull.message}")
    private String password;

    // other info
    private String image;
    private Object specialObject;

} // end class
