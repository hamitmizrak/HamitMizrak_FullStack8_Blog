package com.hamitmizrak.business.dto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Getter @Setter
abstract public class AuditingAwareBaseDto  implements Serializable {
    //Serileştirme
    public static final Long serialVersionUID = 1L;

    //ID
    private Long id;

    //DATE
    private Date systemDate;

    // Auditing atabase hangi kullanıcı ne zaman => ne ekledi veya ne güncelledi
    protected String createdUser;
    protected Date createdDate;
    protected String updatedUser;
    protected Date updatedDate;
}
