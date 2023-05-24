package com.hamitmizrak.data.entity;

import com.hamitmizrak.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
// @SneakyThrows

// ENTITY
@Entity
@Table(name = "roles")
public class RoleEntity extends AuditingAwareBaseEntity implements Serializable {

	// Serileştirme
	public static final Long serialVersionUID = 1L;

	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) //unique id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rolesId;

	@Column(name="role_name",columnDefinition = "varchar(255) default 'Role Adı girmediniz'")
	private String roleName;

	// DATE
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date systemDate;
	//private LocalDate systemDate;
	
} //end class
