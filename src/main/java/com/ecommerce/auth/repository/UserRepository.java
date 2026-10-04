package com.ecommerce.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	// JPAREPOSITORY provides inbuilt methods to communicate with db. it derive sql
	// query and perfom APIs.

	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);

	boolean existsByMobileNumber(String mobileNumber);

}
