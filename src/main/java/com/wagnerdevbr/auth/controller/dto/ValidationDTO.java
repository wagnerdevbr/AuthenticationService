package com.wagnerdevbr.auth.controller.dto;

import lombok.Data;

@Data
public class ValidationDTO {
	
	private String field;
	private String error;
	
	public ValidationDTO(String field, String error) {
		this.field = field;
		this.error = error;
	}

}
