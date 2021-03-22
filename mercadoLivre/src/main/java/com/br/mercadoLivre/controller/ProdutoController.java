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

import com.br.mercadoLivre.model.Produto;
import com.br.mercadoLivre.repository.CategoriaRepository;
import com.br.mercadoLivre.repository.ProdutoRepository;
import com.br.mercadoLivre.requests.ProdutoRequest;
import com.br.mercadoLivre.response.ProdutoResponse;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	private ProdutoRepository produtoRepository;
	private CategoriaRepository categoriaRepository;

	
	
	public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;
	}

	@Transactional
	@PostMapping
	private ResponseEntity<ProdutoResponse> cadastrar (@RequestBody @Valid ProdutoRequest produtoRequest, UriComponentsBuilder uriBuilder){
		
		Produto produto = produtoRequest.converter(categoriaRepository);
		
		produtoRepository.save(produto);
		
		ProdutoResponse produtoRes = new ProdutoResponse(produto);
	
		URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();

		return ResponseEntity.created(uri).body(produtoRes);
		
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<ProdutoResponse> detalhar  (@PathVariable Long id){
		
		Optional<Produto> produto = produtoRepository.findById(id);
		if(produto.isPresent()) {
			return ResponseEntity.ok(new ProdutoResponse(produto.get()));
		}
		return ResponseEntity.notFound().build();
		
		
	}
	

}
