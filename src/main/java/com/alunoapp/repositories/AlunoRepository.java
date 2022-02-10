package com.alunoapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alunoapp.Enums.Status;
import com.alunoapp.models.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	@Query("select a from Aluno a where a.status = :status")
	public List<Aluno> findByStatus();
	
	@Query("SELECT a FROM Aluno a WHERE a.nome LIKE %:nome% AND a.status = :status")
	public List<Aluno> findByNomeLikeAndStatus(@Param("nome") String nome, @Param("status") Status status);
	
	public List<Aluno> findByNomeContainingIgnoreCase(String nome);

}
