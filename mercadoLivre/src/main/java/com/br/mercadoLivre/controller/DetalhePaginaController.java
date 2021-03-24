package com.br.mercadoLivre.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.mercadoLivre.repository.OpiniaoRepository;
import com.br.mercadoLivre.repository.PerguntaRepository;
import com.br.mercadoLivre.repository.ProdutoRepository;
import com.br.mercadoLivre.response.DetalhePaginaResponse;

@RestController
@RequestMapping("/detalhe")
public class DetalhePaginaController {
	
	private ProdutoRepository produtoRepository;
	private PerguntaRepository perguntaRepository;
	private OpiniaoRepository opiniaoRepository;
	
	
	public DetalhePaginaController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository,
			OpiniaoRepository opiniaoRepository) {
		this.produtoRepository = produtoRepository;
		this.perguntaRepository = perguntaRepository;
		this.opiniaoRepository = opiniaoRepository;
	}
	
	
	@GetMapping("/{id}")
	private ResponseEntity<DetalhePaginaResponse> busca(@PathVariable Long id){
		
		DetalhePaginaResponse detalhes = new DetalhePaginaResponse(produtoRepository,
				perguntaRepository, opiniaoRepository, id);
		
		
		return ResponseEntity.ok().body(detalhes);

		
	}
	
	

}
