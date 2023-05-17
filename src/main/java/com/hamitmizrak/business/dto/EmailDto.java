package com.hamitmizrak.business.dto;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDto implements Serializable {
    public static final Long serialVersionUID=1L;

    private String emailTo;
    private String[] toArray;

    private String emailFrom;
    private String emailSubject;

    private String emailCc;
    private String[] ccArray;

    private String emailBcc;
    private String[] bccArray;

    @Builder.Default
    private Date sentDate=new Date(System.currentTimeMillis());

    //@Builder.Default
    private String emailText;  //="Üyeliğinizi aktifleşmesine son bir adım lütfen linke tıklayınız. " + "http://localhost:2222/user/api/v1/confirm?token=";

    @Builder.Default
    private String image="picture.png";
}
