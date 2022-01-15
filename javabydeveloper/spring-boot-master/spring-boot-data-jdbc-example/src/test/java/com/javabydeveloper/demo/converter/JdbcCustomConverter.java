package com.javabydeveloper.demo.converter;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

@Configuration
public class JdbcCustomConverter extends AbstractJdbcConfiguration {

	@Override
	public JdbcCustomConversions jdbcCustomConversions() {

		return new JdbcCustomConversions(Arrays.asList(new UserIdReadConverter(), new UserIdWriteConverter()));
	}

	@ReadingConverter
	static class UserIdReadConverter implements Converter<Long, UserId> {

		@Override
		public UserId convert(Long value) {
			return new UserId(value);
		}
	}

	@WritingConverter
	static class UserIdWriteConverter implements Converter<UserId, Long> {

		@Override
		public Long convert(UserId source) {
			return source.getId();
		}
	}
}
