package com.byrc.user.managment.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: CustomError
 * @author Breymer Robles
 * @since: 2022-02-05
 * @version 1.0
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomError {
	private HttpStatus status;
	@Builder.Default
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private final LocalDateTime timestamp = LocalDateTime.now();
	@Builder.Default
	private final List<String> errors = new ArrayList<>();

	public void addError(final String error) {
		this.errors.add(error);
	}
}
