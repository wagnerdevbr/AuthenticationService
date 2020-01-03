package com.wagnerdevbr.auth.enums;

public enum ModulesEnum {

	SEARCH_USER("SEARCH_USER", "Search users"),
	ADD_USER("ADD_USER","Add users");

	private String code;
	private String description;

	ModulesEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static String getDescriptionByCode(String code) {
		String desc = null;
		for (int i = 0; i < values().length; i++) {
			if (values()[i].getCode().equals(code)) {
				desc = values()[i].getDescription();
				break;
			}
		}
		return desc;
	}
	
	public static ModulesEnum getEnumByCode(String code) {
		ModulesEnum selected = null;
		for (int i = 0; i < values().length; i++) {
			if (values()[i].getCode().equals(code)) {
				selected = values()[i];
				break;
			}
		}
		return selected;
	}
}
