package com.ecommerce.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.auth.dto.LoginRequest;
import com.ecommerce.auth.dto.LoginResponse;
import com.ecommerce.auth.dto.RegisterRequest;
import com.ecommerce.auth.dto.RegisterResponse;
import com.ecommerce.auth.dto.UserResponse;
import com.ecommerce.auth.service.AuthService;

import jakarta.validation.Valid;

@RestController
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/auth/register")
	public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {

		return authService.register(request);

	}

	@PostMapping("/auth/login")
	public LoginResponse login(@Valid @RequestBody LoginRequest request) {

		return authService.login(request);
	}

	@GetMapping("/auth/users/{id}")
	public UserResponse getUserById(@Valid @PathVariable Long id) {
		return authService.getUserById(id);
	}
	
	
	/**
	 * Auth Service lo useful APIs
	 * 
	 * 
		API                                Method                 Purpose 
		/auth/register                      POST                  New user registration ✅
		/auth/login                         POST                  Login + JWT ✅
		/auth/users/{id}                    GET                   User details ✅
		/auth/validate-token                GET                   JWT token valid aa check
		/auth/me                            GET                   Current logged-in user details
	    /auth/change-password               PUT                   Password change
		/auth/forgot-password               POST                  Forgot password flow
		/auth/refresh-token                 POST                  New JWT generate
		
	 */

}
