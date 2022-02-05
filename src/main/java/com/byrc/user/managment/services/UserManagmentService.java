package com.byrc.user.managment.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.byrc.user.managment.domain.models.UserDomainModel;
import com.byrc.user.managment.domain.models.UserExist;
import com.byrc.user.managment.entities.User;
import com.byrc.user.managment.exceptions.ApplicationException;
import com.byrc.user.managment.exceptions.TechnicalException;
import com.byrc.user.managment.mappers.Mapper;
import com.byrc.user.managment.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @ClassName: UserManagmentService
 * @author SKG
 * @since: 2022-02-05
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class UserManagmentService {
	private final Mapper<UserDomainModel, User> userMapper;
	private final UserRepository userRepository;

	public void saveUser(final UserDomainModel userModel) throws ApplicationException {
		try {
			Optional<User> userDB = getUserByEmail(userModel.getEmail());
			final User user = userDB.map(u -> {
				u.setFirstName(userModel.getFirstName());
				u.setLastName(userModel.getLastName());
				return u;
			}).orElse(userMapper.mapToEntity(userModel));
			userRepository.save(user);
		} catch (final DataAccessException e) {
			log.error("UserManagmentService: Error saving user {}", e.getMessage(), e);
			throw new TechnicalException("Error Saving user", e);
		}

	}

	public UserExist isUserExistsByEmail(final String email) throws ApplicationException {
		try {
			return UserExist.builder().exist(getUserByEmail(email).isPresent()).build();
		} catch (final DataAccessException e) {
			log.error("UserManagmentService: Error validating user {}", e.getMessage(), e);
			throw new TechnicalException("Error Validating user", e);
		}
	}

	public List<UserDomainModel> getUsers() throws ApplicationException {
		log.info("UserManagmentService: Getting all users");
		try {
			List<User> users = userRepository.findAll();
			return users.stream().map(userMapper::mapToDomainModel).collect(Collectors.toList());
		} catch (DataAccessException e) {
			log.error("UserManagmentService: Error getting Users {}", e.getMessage(), e);
			throw new TechnicalException("Error getting User By Email", e);
		}
	}

	private Optional<User> getUserByEmail(final String email) {

		return userRepository.findUserByEmail(email);

	}
}
