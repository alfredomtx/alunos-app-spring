package com.alunoapp.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alunoapp.models.Aluno;
import com.alunoapp.models.Usuario;
import com.alunoapp.repositories.UsuarioRepository;
import com.alunoapp.services.UsuarioService;
import com.alunoapp.util.Util;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("aluno", new Aluno());
		return mv;
	}

	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Login/login");
		mv.addObject("usuario", new Usuario());
		return mv;
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return login();
	}

	@PostMapping("/loginSubmit")
	public ModelAndView loginSubmit(@Valid Usuario usuario, BindingResult result, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", usuario);
		mv.setViewName("Login/login");

		if (result.hasErrors()) {
			return mv;
		}

		Usuario userLogin;

		try {
			userLogin = usuarioService.loginUser(usuario.getEmail(), Util.md5(usuario.getSenha()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			mv.addObject("loginError", e.getMessage());
			return mv;
		}
		
		mv.setViewName("redirect:/index");


		session.setAttribute("usuarioLogado", userLogin);

		return mv;
	}

	@PostMapping("/cadastroSubmit")
	public String cadastroSubmit(Usuario usuario) throws Exception {

		System.out.println(usuario);

		usuarioService.salvarUsuario(usuario);

		return "redirect:/";
	}

	@GetMapping("/cadastro")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Login/cadastro");
		mv.addObject("usuario", new Usuario());
		return mv;
	}
}
