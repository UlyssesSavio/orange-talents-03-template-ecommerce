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

import com.br.mercadoLivre.model.Categoria;
import com.br.mercadoLivre.repository.CategoriaRepository;
import com.br.mercadoLivre.requests.CategoriaRequest;
import com.br.mercadoLivre.response.CategoriaResponse;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	
	private CategoriaRepository categoriaRepository;

	public CategoriaController(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}
	
	@Transactional
	@PostMapping
	private ResponseEntity<CategoriaResponse> cadastrar (@RequestBody @Valid CategoriaRequest categoriaRequest, UriComponentsBuilder uriBuilder){
		
		Categoria categoria = categoriaRequest.converter(categoriaRepository);
		
		categoriaRepository.save(categoria);
		
		CategoriaResponse categoriaRes = new CategoriaResponse(categoria);
	
		URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();

		return ResponseEntity.created(uri).body(categoriaRes);
		
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<CategoriaResponse> detalhar  (@PathVariable Long id){
		
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if(categoria.isPresent()) {
			return ResponseEntity.ok(new CategoriaResponse(categoria.get()));
		}
		return ResponseEntity.notFound().build();
		
		
	}
	
	

}
