package br.com.desafio.DesafioCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.DesafioCadastro.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
