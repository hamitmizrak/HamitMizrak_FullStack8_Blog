package com.hamitmizrak.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;
// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

// EMAIL
@Entity
@Table(name="email")
public class EmailEntity extends BaseEntity implements Serializable {

    //SERILESTIRME
    public static final Long serialVersionUID = 1L;

    // EMAILT TO
    @Column(name = "email_to")
    private String emailTo; //KİME

    // EMAIL FROM
    @Column(name = "email_from")
    //@Value("${spring.mail.username}")
    private String emailFrom; //KİMDEN GELİYOR

    // EMAIL SUBJECT
    @Column(name = "email_subject")
    private String emailSubject; //KONU

   /*
    @Column(name="email_cc")
    private String emailCc;// CC

    @Column(name="email_bcc")
    private String emailBcc; //BCC
    */

    // EMAIL TEXT
    @Column(name = "email_text")
    private String emailText;

    // EMAIL IMAGE
    @Column(name = "email_image")
    private String image = "image.png";

    // EMAIL URL
    @Column(name = "email_url") //,columnDefinition = " default 'picture.jpg"
    @Lob // Lob: Large Object
    private String URL = "http://localhost:4444/";

    // EMAIL DATE
    @Column(name = "email_date")
    private Date sentDate; //NE ZAMAN
}
