package com.br.mercadoLivre.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.mercadoLivre.model.Usuario;
import com.br.mercadoLivre.repository.UsuarioRepository;
import com.br.mercadoLivre.requests.UsuarioRequest;
import com.br.mercadoLivre.response.UsuarioResponse;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	
	private UsuarioRepository usuarioRepository;

	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Transactional
	@PostMapping
	private ResponseEntity<UsuarioResponse> cadastrar (@RequestBody @Valid UsuarioRequest usuarioRequest, UriComponentsBuilder uriBuilder){
		
		Usuario usuario = usuarioRequest.converter();
		
		usuarioRepository.save(usuario);
		
		UsuarioResponse usuarioRes = new UsuarioResponse(usuario);
	
		URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();

		return ResponseEntity.created(uri).body(usuarioRes);
		
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<UsuarioResponse> detalhar  (@PathVariable Long id){
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario.isPresent()) {
			return ResponseEntity.ok(new UsuarioResponse(usuario.get()));
		}
		return ResponseEntity.notFound().build();
		
		
	}
	
	

}
