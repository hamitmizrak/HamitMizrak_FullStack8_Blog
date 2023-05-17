package com.hamitmizrak.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.io.Serializable;

// LOMBOK
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
// @SneakyThrows

@Entity
@Table(name="email")
// 1(UserEntity)- 1(TokenCEntity) -
public class EmailSendEntity extends BaseEntity implements Serializable {
    public static final Long serialVersionUID=1L;

    @Column(name = "email_to")
    private String emailTo;

    @Column(name = "email_from")
    private String emailFrom;

    @Column(name = "email_subject")
    private String emailSubject;

    @Column(name = "email_cc")
    private String emailCc;

    @Column(name = "email_bcc")
    private String emailBcc;

    @Column(name = "email_text")
    private String emailText;

    // LOB : büyük veriler
    @Lob
    @Column( columnDefinition = "varchar(255) default 'picture.png'")
    private String image;
}
