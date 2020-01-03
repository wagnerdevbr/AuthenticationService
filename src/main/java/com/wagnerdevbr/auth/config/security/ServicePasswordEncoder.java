package com.wagnerdevbr.auth.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ServicePasswordEncoder implements PasswordEncoder {
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	protected static Logger logger = LoggerFactory.getLogger(ServicePasswordEncoder.class);

	@Override
	public String encode(CharSequence rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

}
