package com.byrc.user.managment.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName: UserExist
 * @author SKG
 * @since: 2022-02-05
 * @version 1.0
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class UserExist {
	private boolean exist;
}
