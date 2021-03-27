package br.com.desafio.DesafioCadastro.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.desafio.DesafioCadastro.controller.dto.UsuarioDto;
import br.com.desafio.DesafioCadastro.controller.form.AtualizaUsuarioForm;
import br.com.desafio.DesafioCadastro.controller.form.UsuarioForm;
import br.com.desafio.DesafioCadastro.modelo.Usuario;
import br.com.desafio.DesafioCadastro.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public Page<UsuarioDto> listar(
			@PageableDefault(sort = "email", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return UsuarioDto.converter(usuarios);
	}

	@GetMapping("/{email}")
	public ResponseEntity<UsuarioDto> buscaUsuarioEmail(@PathVariable String email) {
		Optional<Usuario> usuario = usuarioRepository.findById(email);
		if (usuario.isPresent()) {
			return ResponseEntity.ok(new UsuarioDto(usuario.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<UsuarioDto> cadastro(@Valid @RequestBody UsuarioForm form, UriComponentsBuilder uriBuilder) {
		Usuario usuario = form.converter();
		usuarioRepository.save(usuario);

		URI uri = uriBuilder.path("/usuario/{email}").buildAndExpand(form.getEmail()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}

	@PutMapping("/{email}")
	@Transactional
	public ResponseEntity<UsuarioDto> atualiza(@PathVariable String email,
			@RequestBody @Valid AtualizaUsuarioForm form) {
		Optional<Usuario> optional = usuarioRepository.findById(email);
		if (optional.isPresent()) {
			Usuario usuario = form.atualizar(email, usuarioRepository);
			return ResponseEntity.ok(new UsuarioDto(usuario));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{email}")
	public ResponseEntity<?> remover(@PathVariable String email) {
		Optional<Usuario> usuario = usuarioRepository.findById(email);
		if (usuario.isPresent()) {
			usuarioRepository.deleteById(email);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
