package com.hamitmizrak.business.dto;

import com.hamitmizrak.user.UserDetailBaseEntityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.util.Collection;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
// @SneakyThrows

public class UserDto extends UserDetailBaseEntityDto {
    private String name;
    private String surname;
    private String email;
    private String password;

    // ROLES (ENUM)
    // @Builder.Default // Default bileşen için kullanılır.
    // private ERoles userRoles=ERoles.USER;
    private Collection<RoleDto> roles;
} //end class
