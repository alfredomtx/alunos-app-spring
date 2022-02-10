package com.alunoapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.alunoapp.Enums.Curso;
import com.alunoapp.Enums.Status;

@Entity
@Table(name="aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome")
	@Size(min = 5, max = 100, message = "O nome deve ter entre 5 e 100 caractéres.")
	@NotBlank(message = "O nome não pode ser vazio.")
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Campo curso não selecionado.")
	private Curso curso;
	
	@NotBlank(message = "Matricula não gerada.")
	private String matricula;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Campo status não selecionado.")
	private Status status;
	
	@NotBlank(message = "Campo turno não preenchido.")
	private String turno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", curso=" + curso + ", matricula=" + matricula + ", status="
				+ status + ", turno=" + turno + "]";
	}

	
}
