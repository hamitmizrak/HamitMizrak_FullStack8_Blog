package com.hamitmizrak.business.dto;

import com.hamitmizrak.annotation.RoleNameUnique;
import com.hamitmizrak.audit.AuditingAwareBaseDto;
import com.hamitmizrak.util.ERoles;
import jakarta.validation.constraints.NotEmpty;
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
@Log4j2
@Builder
// @SneakyThrows
public class RoleDto extends AuditingAwareBaseDto implements Serializable {

    // SERILEŞTIRME
    public static final long serialVersionUID=1L;

    // ROLE ID
    private Long rolesId;

    // ROLE NAME
    // Eğer Bir kullanıcı Admin belirlememişse Bu kullanıcı USER olduk
    @NotEmpty(message = "{role.name.validation.constraints.NotNull.message}")
    @RoleNameUnique // Kendi Annotation RolName yazdım
    @Builder.Default
    private String roleName= ERoles.USER.toString();

} //end class
