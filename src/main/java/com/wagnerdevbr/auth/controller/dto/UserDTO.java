package com.wagnerdevbr.auth.controller.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Data;

@Data
public class UserDTO {
	private String login;
	private String password;

	public UserDTO(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(login, password);
	}

}
