package br.com.desafio.DesafioCadastro.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.desafio.DesafioCadastro.modelo.Usuario;

public class UsuarioDto {

	private String nome;
	private String email;
	private LocalDateTime dataCriacao;

	public UsuarioDto (Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.dataCriacao = usuario.getDataCriacao();
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	
	public static Page<UsuarioDto> converter (Page<Usuario> usuario){
		return usuario.map(UsuarioDto::new);
	}

}
