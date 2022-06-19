package com.alunoapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>{

		public User findByEmail(String email);
		
		@Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password" )
		public User buscarLogin(String email, String senha);
		
}