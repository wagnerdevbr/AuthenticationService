package com.wagnerdevbr.auth.config;

import java.io.Serializable;

import com.wagnerdevbr.auth.entity.User;
import com.wagnerdevbr.auth.enums.ModulesEnum;

public class AuthenticationManagerRoles implements Serializable{

	private static final long serialVersionUID = 906292143661684948L;

	private static User authenticatedUser;
	
	private AuthenticationManagerRoles() {
		
	}
	
	public static User getAuthenticatedOperador() throws Exception {
		if(authenticatedUser==null) {
			throw new Exception("User is not logged in!");
		}
		return authenticatedUser;
	}
	
	public static void setAuthenticatedUser(User user) {
		authenticatedUser = user;
	}
	
	public static Boolean isUserAllowedModule(User user, ModulesEnum... modules) throws Exception  {
		for (ModulesEnum m:modules) {
			Boolean ret = authenticatedUser.getAuthorities().stream().filter(i->i.getAuthority().trim().equalsIgnoreCase(m.getCode())).count()>0?Boolean.TRUE:Boolean.FALSE;
			
			if(ret) {
				return Boolean.TRUE;
			}
		}
		
		return Boolean.FALSE;

	}
	
}
