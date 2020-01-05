package com.wagnerdevbr.auth.config;

import java.io.Serializable;

import com.wagnerdevbr.auth.entity.User;
import com.wagnerdevbr.auth.enums.ModulesEnum;
import com.wagnerdevbr.auth.exception.AuthenticationFailException;

public class AuthenticationManagerRoles implements Serializable{

	private static final long serialVersionUID = 906292143661684948L;

	private static User authenticatedUser;
	
	private AuthenticationManagerRoles() {
		
	}
	
	public static User getAuthenticatedOperador() throws AuthenticationFailException {
		if(authenticatedUser==null) {
			throw new AuthenticationFailException("User is not logged in!");
		}
		return authenticatedUser;
	}
	
	public static void setAuthenticatedUser(User user) {
		authenticatedUser = user;
	}
	
	public static boolean isUserAllowedModule(User user, ModulesEnum... modules)   {
		for (ModulesEnum m:modules) {
			boolean ret = user.getAuthorities().stream().filter(i->i.getAuthority().trim().equalsIgnoreCase(m.getCode())).count()>0?Boolean.TRUE:Boolean.FALSE;
			
			if(ret) {
				return Boolean.TRUE;
			}
		}
		
		return Boolean.FALSE;

	}
	
}
