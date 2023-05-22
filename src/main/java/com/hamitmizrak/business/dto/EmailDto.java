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

    private Long id;

    @NotEmpty(message = "{email.to.validation.constraints.NotNull.message}")
    private String emailTo; //KİME
    //private String[] emailToArray; //KİMLERE

    @Value("${spring.mail.username}")
    //@Builder.Default
    private String emailFrom; //KİMDEN GELİYOR

    @NotEmpty(message = "{email.subject.validation.constraints.NotNull.message}")
    private String emailSubject; //KONU

    private String emailCc;// CC
    private String[] emailCcArray;

    private String emailBcc; //BCC
    private String[] emailBccArray;

    @Builder.Default
    private Date sentDate=new Date(System.currentTimeMillis()); //NE ZAMAN

    @NotEmpty(message = "{email.text.validation.constraints.NotNull.message}")
    private String emailText;

    @Builder.Default
    private String image="image.png";

    @Builder.Default
    private String URL="http://localhost:2222/";

    @Builder.Default
    private Date createdDate=new Date(System.currentTimeMillis());
}
