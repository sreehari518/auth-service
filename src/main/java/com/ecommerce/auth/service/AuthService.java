package com.ecommerce.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.auth.dto.LoginRequest;
import com.ecommerce.auth.dto.LoginResponse;
import com.ecommerce.auth.dto.RegisterRequest;
import com.ecommerce.auth.dto.RegisterResponse;
import com.ecommerce.auth.dto.UserResponse;
import com.ecommerce.auth.entity.User;
import com.ecommerce.auth.exception.EmailAlreadyExistsException;
import com.ecommerce.auth.exception.InvalidCredentialsException;
import com.ecommerce.auth.exception.MobileNumberAlreadyExistsException;
import com.ecommerce.auth.repository.UserRepository;
import com.ecommerce.auth.security.JwtService;

@Service
public class AuthService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtService;

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	public RegisterResponse register(RegisterRequest request) {

		if (userRepository.existsByEmail(request.getEmail())) { // email exixts it means(true) it throws Exception
																// otherwise continue registration.
			throw new EmailAlreadyExistsException("Email already registered");
		}

		if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
			throw new MobileNumberAlreadyExistsException("Mobile numer already registered");
		}

		User user = new User();

		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setMobileNumber(request.getMobileNumber());
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		user.setRole("USER");

		User savedUser = userRepository.save(user);

		return new RegisterResponse(

				savedUser.getName(), savedUser.getEmail(), savedUser.getRole());

	}

	public LoginResponse login(LoginRequest request) {

		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

		// email does not found in db it can't show " null" it bad approch. Thats why we
		// use[ Optional<user> ] in Repository it throws Exception.

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {

			throw new InvalidCredentialsException("Invalid Password");
		}

		LoginResponse response = new LoginResponse();

		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setToken(jwtService.generateToken(user.getEmail(), user.getRole()));

		return response;
	}

	public UserResponse getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not Found"));

		return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getMobileNumber(), user.getRole());
	}
}
