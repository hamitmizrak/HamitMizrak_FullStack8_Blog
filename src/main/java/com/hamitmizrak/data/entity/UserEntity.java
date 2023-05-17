package com.hamitmizrak.data.entity;

import com.hamitmizrak.audit.AuditingAwareBaseEntity;
import com.hamitmizrak.util.ERoles;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

// LOMBOK
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
// @SneakyThrows

@Entity
@Table(name = "blog_user")
//@Component
//@Where(clause="roles_id=1")
// 1(UserEntity)- 1(TokenCEntity)
// N(User) - M(Roles)
//public class UserEntity extends AuditingAwareBaseClass{
public class UserEntity extends AuditingAwareBaseEntity implements UserDetails {

    // ID
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //unique id
    @GeneratedValue(strategy = GenerationType.AUTO) //unique id
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String surname;
    private String email;
    private String password;

    // DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemDate;
    //private LocalDate systemDate;

    // @Embedded  // @Embeddable // @EmbeddedId

    // ROLES
    // @Column(name = "roles_name")
    // private ERoles userRoles;

    // 1 .YOL Relation
    /*
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "user_id")},
                inverseJoinColumns = {@JoinColumn(name = "roles_id",referencedColumnName = "roles_id")} )
               private List<RoleEntity> roles;
    */
    // 2.YOL Relation
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
    // @OrderBy
    // @OrderBy("rolesId DESC")
    private Collection<RoleEntity> roles;

    // UserDetails DEFAULT
    // Kullanıcı başlangıçta kilitli yani sisteme giremezsin
    @Column(name = "locked")
    private Boolean isAccountNonLocked;

    // Kullanıcını Hesap süresi Doldu mu ?
    @Column(name = "account_expired")
    private Boolean isAccountNonExpired;

    // Kulllanıcının Bilgi Süresi Doldu mu?
    @Column(name = "credentials_expired")
    private Boolean isCredentialsNonExpired;

    // Kullanıcı Aktif mi ? pasif mi
    @Column(name = "enabled")
    private Boolean isEnabled;

    // UserDetails (implements)
    // ROLES
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(ERoles.USER.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    //PASSWORD
    @Override
    public String getPassword() {
        return this.password;
    }

    //USERNAME
    @Override
    public String getUsername() {
        return this.email;
    }

    //Kullanıcını Hesap süresi Doldu mu ?
    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    // Kullanıcı Hesabı kilitli mi ?
    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    // Kulllanıcının Bilgi Süresi Doldu mu?
    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    // Kullanıcı Aktif mi, Pasif mi ?(kullanıcı Sistemdeki)
    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
