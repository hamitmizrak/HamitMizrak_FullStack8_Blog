package com.hamitmizrak.audit;

import lombok.Data;


import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data
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

