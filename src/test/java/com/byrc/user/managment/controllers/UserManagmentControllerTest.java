package com.byrc.user.managment.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.byrc.user.managment.entities.User;
import com.byrc.user.managment.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @ClassName: UserManagmentControllerTest
 * @author SKG
 * @since: 2022-02-05
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class UserManagmentControllerTest {
	private static final String NAME = "abc";
	private static final String TEST_MAIL = "test@gmail.com";
	private static final String API = "/api/v1/user/";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	public void beforeEach() {
		userRepository.deleteAll();
	}

	@Test
	void saveUserTest() {
		try {

			var body = new HashMap<String, Object>();
			body.put("email", TEST_MAIL);
			body.put("firstName", NAME);
			body.put("lastName", NAME);

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			var requestJson = ow.writeValueAsString(body);

			var request = MockMvcRequestBuilders.post(API).accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON).content(requestJson);
			mockMvc.perform(request).andExpect(status().isOk()).andReturn();

		} catch (Exception e) {
			fail();
		}
	}

	@Test
	void getUsersTest() {
		try {
			userRepository.save(User.builder().email(TEST_MAIL).firstName(NAME).lastName(NAME).build());
			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API)

					.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

			String content = result.getResponse().getContentAsString();
			assertEquals("[{\"email\":\"test@gmail.com\",\"firstName\":\"abc\",\"lastName\":\"abc\"}]", content);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	void isUserExistsTrueTest() {
		try {
			userRepository.save(User.builder().email(TEST_MAIL).firstName(NAME).lastName(NAME).build());
			MockHttpServletRequestBuilder request = MockMvcRequestBuilders
					.get(API + "validate-email/?email=" + TEST_MAIL)

					.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

			String content = result.getResponse().getContentAsString();
			assertEquals("{\"exist\":true}", content);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	void isUserExistsFalseTest() {
		try {
			MockHttpServletRequestBuilder request = MockMvcRequestBuilders
					.get(API + "validate-email/?email=" + TEST_MAIL)

					.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

			String content = result.getResponse().getContentAsString();
			assertEquals("{\"exist\":false}", content);
		} catch (Exception e) {
			fail();
		}
	}
}
