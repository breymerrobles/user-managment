package com.byrc.user.managment.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.byrc.user.managment.domain.models.UserDomainModel;
import com.byrc.user.managment.entities.User;

import lombok.RequiredArgsConstructor;

/**
 * @ClassName: UserMapper
 * @author SKG
 * @since: 2022-02-05
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<UserDomainModel, User> {

	private final ModelMapper modelMapper;

	public UserDomainModel mapToDomainModel(final User user) {
		return modelMapper.map(user, UserDomainModel.class);
	}

	public User mapToEntity(final UserDomainModel user) {
		return modelMapper.map(user, User.class);
	}

}
