package com.byrc.user.managment.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import com.byrc.user.managment.domain.models.UserDomainModel;
import com.byrc.user.managment.domain.models.UserExist;
import com.byrc.user.managment.entities.User;
import com.byrc.user.managment.exceptions.ApplicationException;
import com.byrc.user.managment.exceptions.TechnicalException;
import com.byrc.user.managment.mappers.UserMapper;
import com.byrc.user.managment.repositories.UserRepository;

/**
 * @ClassName: UserManagmentServiceTest
 * @author Breymer Robles
 * @since: 2022-02-05
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("serial")
class UserManagmentServiceTest {
	private static final String NAME = "abc";
	private static final String NAME_UPDATE = "def";
	private static final String TEST_MAIL = "test@gmail.com";
	@Mock
	private UserRepository userRepository;
	@Mock
	private UserMapper userMapper;
	private UserManagmentService userManagmentService;

	@BeforeEach
	public void beforeEach() {
		userManagmentService = new UserManagmentService(userMapper, userRepository);
	}

	@Test
	void saveUserCreateWhenNoExistTest() {
		try {
			User userMapped = User.builder().email(TEST_MAIL).firstName(NAME).lastName(NAME).build();
			when(userRepository.findUserByEmail(TEST_MAIL)).thenReturn(Optional.empty());
			UserDomainModel userModel = UserDomainModel.builder().email(TEST_MAIL).firstName(NAME).lastName(NAME)
					.build();
			when(userMapper.mapToEntity(userModel)).thenReturn(userMapped);
			userManagmentService.saveUser(userModel);
			verify(userRepository, times(1)).save(userMapped);
		} catch (ApplicationException e) {
			fail();
		}
	}

	@Test
	void saveUserUpdateWhenExistTest() {
		try {
			User userMapped = User.builder().email(TEST_MAIL).firstName(NAME).lastName(NAME).build();
			when(userRepository.findUserByEmail(TEST_MAIL)).thenReturn(Optional.of(userMapped));
			UserDomainModel userModel = UserDomainModel.builder().email(TEST_MAIL).firstName(NAME_UPDATE)
					.lastName(NAME_UPDATE).build();
			userManagmentService.saveUser(userModel);
			verify(userRepository, times(1)).save(userMapped);
			assertEquals(NAME_UPDATE, userMapped.getFirstName());
			assertEquals(NAME_UPDATE, userMapped.getLastName());
		} catch (ApplicationException e) {
			fail();
		}
	}

	@Test
	void updateUserWithException() throws ApplicationException {
		UserDomainModel userModel = UserDomainModel.builder().email(TEST_MAIL).firstName(NAME_UPDATE)
				.lastName(NAME_UPDATE).build();
		Mockito.when(userRepository.findUserByEmail(TEST_MAIL)).thenThrow(new DataAccessException("Error") {
		});
		Assertions.assertThrowsExactly(TechnicalException.class, () -> userManagmentService.saveUser(userModel));
		verify(userRepository, times(1)).findUserByEmail(TEST_MAIL);
	}

	@Test
	void isUserExistsByEmailNotFoundTest() {
		try {
			when(userRepository.findUserByEmail(TEST_MAIL)).thenReturn(Optional.ofNullable(null));
			UserExist userExistsByEmail = userManagmentService.isUserExistsByEmail(TEST_MAIL);
			assertFalse(userExistsByEmail.isExist());
			verify(userRepository, times(1)).findUserByEmail(TEST_MAIL);
		} catch (ApplicationException e) {
			fail();
		}
	}

	@Test
	void isUserExistsByEmailFoundTest() {
		try {
			User userMapped = User.builder().email(TEST_MAIL).firstName(NAME).lastName(NAME).build();
			when(userRepository.findUserByEmail(TEST_MAIL)).thenReturn(Optional.ofNullable(userMapped));
			UserExist userExistsByEmail = userManagmentService.isUserExistsByEmail(TEST_MAIL);
			assertTrue(userExistsByEmail.isExist());
			verify(userRepository, times(1)).findUserByEmail(TEST_MAIL);
		} catch (ApplicationException e) {
			fail();
		}
	}

	@Test
	void isUserExistsByEmailException() throws ApplicationException {
		Mockito.when(userRepository.findUserByEmail(TEST_MAIL)).thenThrow(new DataAccessException("Error") {
		});
		Assertions.assertThrowsExactly(TechnicalException.class,
				() -> userManagmentService.isUserExistsByEmail(TEST_MAIL));
		verify(userRepository, times(1)).findUserByEmail(TEST_MAIL);
	}

	@Test
	void getUsersSuccessTest() {
		try {
			User userDB = User.builder().id(1L).email(TEST_MAIL).firstName(NAME).lastName(NAME).build();
			when(userMapper.mapToDomainModel(userDB))
					.thenReturn(UserDomainModel.builder().email(TEST_MAIL).firstName(NAME).lastName(NAME).build());

			when(userRepository.findAll()).thenReturn(Collections.singletonList(userDB));
			List<UserDomainModel> users = userManagmentService.getUsers();
			verify(userRepository, times(1)).findAll();
			assertFalse(users.isEmpty());
			assertEquals(NAME, users.get(0).getFirstName());
			assertEquals(NAME, users.get(0).getLastName());
			assertEquals(TEST_MAIL, users.get(0).getEmail());
		} catch (ApplicationException e) {
			fail();
		}
	}

	@Test
	void getUsersSuccessEmptyTest() {
		try {

			when(userRepository.findAll()).thenReturn(Collections.emptyList());
			List<UserDomainModel> users = userManagmentService.getUsers();
			verify(userRepository, times(1)).findAll();
			assertTrue(users.isEmpty());
		} catch (ApplicationException e) {
			fail();
		}
	}

	@Test
	void getUsersException() throws ApplicationException {
		Mockito.when(userRepository.findAll()).thenThrow(new DataAccessException("Error") {
		});
		Assertions.assertThrowsExactly(TechnicalException.class, () -> userManagmentService.getUsers());
		verify(userRepository, times(1)).findAll();
	}

}
