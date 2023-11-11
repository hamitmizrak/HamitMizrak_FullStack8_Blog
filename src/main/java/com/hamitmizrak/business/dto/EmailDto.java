package com.hamitmizrak.business.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDto {

    // ID
    private Long id;

    // EMAIL TO
    @NotEmpty(message = "{email.to.validation.constraints.NotNull.message}")
    private String emailTo; //KİME
    //private String[] emailToArray; //KİMLERE

    // EMAIL FROM
    @Value("${spring.mail.username}")
    //@Builder.Default
    private String emailFrom; //KİMDEN GELİYOR

    // EMAIL SUBJECT
    @NotEmpty(message = "{email.subject.validation.constraints.NotNull.message}")
    private String emailSubject; //KONU

    // EMAIL CC
    private String emailCc;// CC
    private String[] emailCcArray;

    // EMAIL BCC
    private String emailBcc; //BCC
    private String[] emailBccArray;

    // EMAIL TEXT
    @NotEmpty(message = "{email.text.validation.constraints.NotNull.message}")
    private String emailText;

    // IMAGE
    @Builder.Default
    private String image="image.png";

    // URL
    @Builder.Default
    private String URL="http://localhost:2222/";

    // DATE(SEND)
    @Builder.Default
    private Date sentDate=new Date(System.currentTimeMillis()); //NE ZAMAN

    // SYSTEM CREATED DATE
    @Builder.Default
    private Date systemCreatedDate=new Date(System.currentTimeMillis());
}
