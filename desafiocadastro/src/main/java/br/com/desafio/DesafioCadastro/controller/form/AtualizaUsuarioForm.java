package br.com.desafio.DesafioCadastro.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.desafio.DesafioCadastro.modelo.Usuario;
import br.com.desafio.DesafioCadastro.repository.UsuarioRepository;

public class AtualizaUsuarioForm {

	@NotEmpty
	@NotNull
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public Usuario atualizar(String email, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getOne(email);
		usuario.setNome(this.nome);
		return usuario;
	}

}
