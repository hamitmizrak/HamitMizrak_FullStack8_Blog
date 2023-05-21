package com.hamitmizrak.business.dto;

import com.hamitmizrak.audit.AuditingAwareBaseDto;
import com.hamitmizrak.util.ERoles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// LOMBOK
@Data
@AllArgsConstructor
public class RoleDto extends AuditingAwareBaseDto {

    private Long rolesId;

    // Eğer Bir kullanıcı Admin belirlememişse Bu kullanıcı USER olduk
    @Builder.Default
    private String roleName= ERoles.USER.toString();
    //private String roleName;

    // Parametresiz Constructor
    public RoleDto() {
    }

}
