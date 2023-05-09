package com.hamitmizrak.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Builder
@Log4j2

// ENTITY
@Entity
@Table(name = "register")
public class RegisterEntity extends BaseEntity implements Serializable  {
    // Serileştirme
    public static final Long serialVersionUID = 1L;

    // NAME
    //String => columnDefinition = "varchar(255) default 'adınızı girmediniz'"
    // INT    => columnDefinition = "integer default 44"
    @Column(name = "name", columnDefinition = "varchar(255) default 'adınızı girmediniz'")
    private String name;

    // SURNAME
    private String surname;

    // PASSWORD
    @Column(name = "password", columnDefinition = "varchar(255) default 'Hm123456@'")
    private String password;

    // LOB : büyük veriler
    @Lob
    @Column( columnDefinition = "varchar(255) default 'picture.png'")
    private String image;

    // Javada olsun ancak Database olmasın
    @Transient
    private Object specialObject;

    /*
    @Column(name = "is_check", columnDefinition = "boolean default false")
    private String check;
    */

    // EMAİL
    // virgüllü sayı: columnDefinition = "Decimal(10,2) default '100.00'")
    @Column(
            name = "email",
            nullable = true,
            //unique = true, // bunun yerine kendi Annotation yazdım
            length = 100,
            insertable = true,
            updatable = true,
            columnDefinition = "varchar(255) default 'email yazmadiniz'")
    private String email;
}
