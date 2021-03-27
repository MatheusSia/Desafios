package br.com.desafio.DesafioCadastro.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {

	private String nome;

	@Id
	private String email;

	private LocalDateTime data = LocalDateTime.now();

	public Usuario() {
	}

	public Usuario(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getDataCriacao() {
		return data;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.data = dataCriacao;
	}

}
