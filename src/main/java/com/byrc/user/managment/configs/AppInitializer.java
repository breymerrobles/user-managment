package com.byrc.user.managment.configs;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: AppInitializer
 * @author SKG
 * @since: 2022-02-05
 * @version 1.0
 */
@Configuration
public class AppInitializer {

	@Bean
	public ModelMapper getModelMapper() {
		final ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(AccessLevel.PRIVATE);
		return modelMapper;
	}

}
