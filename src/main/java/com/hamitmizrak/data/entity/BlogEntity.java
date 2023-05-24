package com.hamitmizrak.data.entity;

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
@Table(name = "blog")
public class BlogEntity extends BaseEntity implements Serializable  {

    // Serileştirme
    public static final Long serialVersionUID = 1L;

    // HEADER
    @Column(name = "header", columnDefinition = "varchar(255) default 'header girmediniz'")
    private String header;

    // CONTENT
    @Column(name = "content", columnDefinition = "varchar(255) default 'content girmediniz'")
    private String content;

    // IMAGE
    // LOB : büyük veriler
    @Lob
    @Column( columnDefinition = "varchar(255) default 'picture.png'")
    private String image;

    // Javada olsun ancak Database olmasın
    /**
    @Transient
    private Object specialObject;*/

    /**
    @Column(name = "is_check", columnDefinition = "boolean default false")
     String  => columnDefinition = "varchar(255) default 'adınızı girmediniz'"
     INT     => columnDefinition = "integer default 44"
     DOUBLE  => columnDefinition = "Decimal(10,2) default '100.00'")
     BOOLEAN => columnDefinition = "boolean default false"
    */

    /**
     @Column(
     name = "email",
     nullable = true,
     unique = true, // bunun yerine kendi Annotation yazdım
     length = 100,
     insertable = true,
     updatable = true,
     columnDefinition = "varchar(255) default 'email yazmadiniz'")
     */
}
