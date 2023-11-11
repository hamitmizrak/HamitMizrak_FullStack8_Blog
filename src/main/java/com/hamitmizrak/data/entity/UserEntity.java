package com.hamitmizrak.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hamitmizrak.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

// ENTITY
@Entity(name="Users")
@Table(name = "users")
public class UserEntity extends AuditingAwareBaseEntity {

    // ManyToMany için kendi ID buraya yazdım BaseEntity yazmadım
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Global Variable (6)
    // Dikkat: message sonunda boşluk olmasın
    // NAME
    private String name;

    // SURNAME
    private String surname;

    // PASSWORD
    @JsonIgnore // backentte giden veriyi saklar
    private String password;

    // PAGE AUTHORIZATION (O Sayfaya yetkili Kişiler)
    @Column(name="page_authorization")
    private boolean pageAuthorization;

    // EMAİL
    private String email;

    // DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemDate;
    //private LocalDate systemDate;

    // ROLE ENTITIY
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();
    //2.YOL
	/*
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
	joinColumns = {
	            @JoinColumn(name = "user_id",referencedColumnName = "user_id")},
	            inverseJoinColumns = {
	                            @JoinColumn(name = "roles_id",referencedColumnName = "roles_id")
	                            }
	             )
	 private List<RoleEntity> roles;
	   */

    // #######################################################
    // USER DETAILS
    // @Embedded
    // @Embeddable
    // @EmbeddedId
    @Embedded
    private UserDetailsEmbeddable userDetailsEmbeddable=new UserDetailsEmbeddable();

} //end class
