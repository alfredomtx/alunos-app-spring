package com.alunoapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alunoapp.Enums.Status;
import com.alunoapp.models.Aluno;
import com.alunoapp.repositories.AlunoRepository;

@Controller
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;

	@GetMapping("/cadastrarAluno")
	public ModelAndView cadastrarAluno(Aluno aluno) {
		ModelAndView mv = new ModelAndView("Aluno/formAluno");

		mv.addObject("aluno", new Aluno());

		return mv;
	}

	@PostMapping("inserirAluno")
	public ModelAndView inserirAluno(@Valid Aluno aluno, BindingResult result, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView();

		if (result.hasErrors()) {
			mv.setViewName("Aluno/formAluno");
			mv.addObject("aluno", aluno);

			// System.out.println(result.getAllErrors());

			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
				// attributes.addFlashAttribute("mensagem", error.getDefaultMessage());
			}

			return mv;
		}

		mv.setViewName("redirect:/listarAlunos");
		System.out.println(aluno);
		alunoRepository.save(aluno);
		return mv;
	}

	@RequestMapping("listarAlunos")
	public ModelAndView listarAlunos() {
		ModelAndView mv = new ModelAndView();

		mv.addObject("alunos", alunoRepository.findAll());
		mv.setViewName("Aluno/listarAlunos");

		return mv;
	}

	@GetMapping(value = "/alterarAluno/{id}")
	public ModelAndView alterarAluno(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("Aluno/alterarAluno");

		Aluno aluno = alunoRepository.getById(id);

		mv.addObject("aluno", aluno);

		return mv;
	}

	@PostMapping(value = "/alterarAluno")
	public String alterarAluno(Aluno aluno) {

		System.out.println(aluno);
		alunoRepository.save(aluno);
		return "redirect:/listarAlunos";
	}

	@GetMapping("/deletarAluno/{id}")
	public String deletarAluno(@PathVariable("id") Long id) {

		alunoRepository.deleteById(id);

		return "redirect:/listarAlunos";
	}

	@GetMapping(value = "/pesquisarAlunos")
	public ModelAndView pesquisarAlunos() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("Aluno/pesquisarAlunos");
		mv.addObject("aluno", new Aluno());

		return mv;
	}

	@PostMapping(value = "/pesquisarAlunos")
	public ModelAndView pesquisarAlunos(@RequestParam(required = false) Aluno aluno) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("Aluno/pesquisarAlunos");
		
		String nome = aluno.getNome();

		System.out.println(nome);

		// Status status = enum Status(statusAluno);

		List<Aluno> alunosList;
		
		

		if (nome == null || nome.trim().isEmpty()) {
			alunosList = alunoRepository.findAll();
		} else {
			alunosList = alunoRepository.findByNomeContainingIgnoreCase(aluno.getNome());
		}

		for (Aluno alunoL : alunosList) {
			System.out.println(alunoL);
		}

		mv.addObject("alunosList", alunosList);

		return mv;

	}

}
