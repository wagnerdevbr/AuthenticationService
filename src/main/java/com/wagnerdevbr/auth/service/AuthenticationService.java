package com.wagnerdevbr.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wagnerdevbr.auth.entity.User;
import com.wagnerdevbr.auth.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String loginOrEmail) throws UsernameNotFoundException {
		
		User user = userRepository.findByLogin(loginOrEmail);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found!");
		}
		return user;
		
	}


}
