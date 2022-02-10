package com.alunoapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alunoapp.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

		public Usuario findByEmail(String email);
		
		@Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha" )
		public Usuario buscarLogin(String email, String senha);
		
}