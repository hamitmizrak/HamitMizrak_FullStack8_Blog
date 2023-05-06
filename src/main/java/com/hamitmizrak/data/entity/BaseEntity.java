package com.hamitmizrak.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hamitmizrak.audit.AuditingAwareBaseClass;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter @Setter // LOMBOK
@JsonIgnoreProperties(value = {"created_date,updated_date"}) // Json'a emir veriyoruz bunları takip etme
@EntityListeners(AuditingEntityListener.class) //Auditing
@MappedSuperclass // Super
abstract public class BaseEntity extends AuditingAwareBaseClass {

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //unique id
    private Long id;

    // DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemDate;
}
