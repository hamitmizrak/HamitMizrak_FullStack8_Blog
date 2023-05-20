package com.hamitmizrak.business.dto;

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
    private String emailTo; //KİME
    //private String[] emailToArray; //KİMLERE

    @Value("${spring.mail.username}")
    //@Builder.Default
    private String emailFrom; //KİMDEN GELİYOR
    private String emailSubject; //KONU

    /*private String emailCc;// CC
    private String[] emailCcArray;

    private String emailBcc; //BCC
    private String[] emailBccArray;*/

    @Builder.Default
    private Date sentDate=new Date(System.currentTimeMillis()); //NE ZAMAN
    private String emailText;

    @Builder.Default
    private String image="image.png";

    @Builder.Default
    private String URL="http://localhost:2222/";

    @Builder.Default
    private Date createdDate=new Date(System.currentTimeMillis());
}
