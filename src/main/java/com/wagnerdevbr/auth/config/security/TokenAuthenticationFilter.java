package com.wagnerdevbr.auth.config.security;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wagnerdevbr.auth.entity.User;
import com.wagnerdevbr.auth.repository.UserRepository;
import com.wagnerdevbr.auth.service.TokenService;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private UserRepository userRepository;

	public TokenAuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
		boolean valido = tokenService.isValidToken(token);
		if (valido) {
			authenticateOperator(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void authenticateOperator(String token) {
		Long userId = tokenService.getUserId(token);
		User user = userRepository.findById(userId).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
