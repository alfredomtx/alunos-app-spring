package com.alunoapp.services;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alunoapp.exceptions.EmailExistsException;
import com.alunoapp.exceptions.LoginException;
import com.alunoapp.exceptions.PasswordCryptoException;
import com.alunoapp.models.Usuario;
import com.alunoapp.repositories.UsuarioRepository;
import com.alunoapp.util.Util;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public void salvarUsuario(Usuario usuario) throws Exception {

		try {
			if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
				throw new EmailExistsException("Já existe um e-mail cadastrado para: " + usuario.getEmail());
			}

			usuario.setSenha(Util.md5(usuario.getSenha()));

		} catch (NoSuchAlgorithmException e) {
			throw new PasswordCryptoException("Erro na criptografia da senha.");
		}

		usuarioRepository.save(usuario);
	}

	public Usuario loginUser(String email, String senha) throws LoginException {

		Usuario userLogin = usuarioRepository.buscarLogin(email, senha);

		if (userLogin == null)
			throw new LoginException("Usuário não encontrado.");

		return userLogin;

	}

}
