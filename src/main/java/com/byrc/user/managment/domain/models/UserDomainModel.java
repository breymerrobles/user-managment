package com.byrc.user.managment.domain.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName: UserDomainModel
 * @author Breymer Robles
 * @since: 2022-02-05
 * @version 1.0
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class UserDomainModel {
	@Email(message = "Email Invalid")
	@NotBlank(message = "Email is empty")
	@EqualsAndHashCode.Include
	private String email;
	@NotBlank(message = "Fisrt Name is empty")
	private String firstName;
	@NotBlank(message = "Last Name is empty")
	private String lastName;
}
