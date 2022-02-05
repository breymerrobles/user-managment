package com.byrc.user.managment.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.byrc.user.managment.domain.models.UserDomainModel;
import com.byrc.user.managment.domain.models.UserExist;
import com.byrc.user.managment.exceptions.ApplicationException;
import com.byrc.user.managment.services.UserManagmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @ClassName: UserManagmentController
 * @author SKG
 * @since: 2022-02-05
 * @version 1.0
 */

@RestController
@RequestMapping(value = "api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
@RequiredArgsConstructor
public class UserManagmentController {

	private final UserManagmentService userManagmentService;

	@PostMapping(value = "/")
	public ResponseEntity<Void> saveUser(@Valid @RequestBody final UserDomainModel userModel)
			throws ApplicationException {
		log.info("UserManagmentController : Saving User ");
		userManagmentService.saveUser(userModel);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/validate-email/")
	public ResponseEntity<UserExist> isUserExists(@RequestParam(required = true) final String email)
			throws ApplicationException {
		return ResponseEntity.ok(userManagmentService.isUserExistsByEmail(email));
	}

	@GetMapping(value = "/")
	public ResponseEntity<List<UserDomainModel>> getUsers() throws ApplicationException {
		return ResponseEntity.ok(userManagmentService.getUsers());
	}

}
