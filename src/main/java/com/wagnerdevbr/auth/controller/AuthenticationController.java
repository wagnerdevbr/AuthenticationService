package com.wagnerdevbr.auth.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wagnerdevbr.auth.config.AuthenticationManagerRoles;
import com.wagnerdevbr.auth.controller.dto.TokenDTO;
import com.wagnerdevbr.auth.controller.dto.UserDTO;
import com.wagnerdevbr.auth.entity.User;
import com.wagnerdevbr.auth.enums.ModulesEnum;
import com.wagnerdevbr.auth.repository.UserRepository;
import com.wagnerdevbr.auth.service.TokenService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	protected static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenService tokenService;
	
	@ApiOperation(value = "User authentication", nickname = "auth", notes = "User authentication to retrieve security token")
    @ApiResponses(value = { 
    @ApiResponse(code = 201, message = "Successful authentication", response = TokenDTO.class),
    @ApiResponse(code = 400, message = "Invalid resquest"),
    @ApiResponse(code = 401, message = "User not found"),
    @ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping
	public ResponseEntity<TokenDTO> auth(@RequestBody @Valid UserDTO userDTO) {
		UsernamePasswordAuthenticationToken loginInformation = userDTO.converter();
		User user = userRepository.findByLogin(userDTO.getLogin());

		try {

			if (user!=null && AuthenticationManagerRoles.isUserAllowedModule(user, ModulesEnum.values())) {

				Authentication authentication = authManager.authenticate(loginInformation);
				String token = tokenService.gerarToken(authentication);
				return ResponseEntity.ok(new TokenDTO(token, "Bearer"));

			} else {
				
				return ResponseEntity.status(401).build();
			}

		} catch (Exception e) {
			logger.debug("Erro na autenticação", e);
			return ResponseEntity.badRequest().build();
		}

	}

}
