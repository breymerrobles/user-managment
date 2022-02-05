package com.byrc.user.managment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName: User
 * @author Breymer Robles
 * @since: 2022-02-05
 * @version 1.0
 */
@Entity
@Table(name = "users_managment")
@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class User {
	@Id
	@GeneratedValue(generator = "usersManagmentIdSeq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "usersManagmentIdSeq", sequenceName = "users_managment_id_seq", allocationSize = 1)
	private Long id;
	@EqualsAndHashCode.Include
	private String email;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;

}
