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

import com.br.mercadoLivre.model.Opiniao;
import com.br.mercadoLivre.repository.OpiniaoRepository;
import com.br.mercadoLivre.repository.ProdutoRepository;
import com.br.mercadoLivre.requests.OpiniaoRequest;
import com.br.mercadoLivre.response.OpiniaoResponse;

@RestController
@RequestMapping("/opiniao")
public class OpiniaoController {
	
	private OpiniaoRepository opiniaoRepository;
	private ProdutoRepository produtoRepository;
	
	
	public OpiniaoController(OpiniaoRepository opiniaoRepository, ProdutoRepository produtoRepository) {
		this.opiniaoRepository = opiniaoRepository;
		this.produtoRepository = produtoRepository;
	}
	
	@Transactional
	@PostMapping
	private ResponseEntity<OpiniaoResponse> cadastrar (@RequestBody @Valid OpiniaoRequest opiniaoRequest, UriComponentsBuilder uriBuilder){
		
		Opiniao opiniao = opiniaoRequest.converter(produtoRepository);
		
		opiniaoRepository.save(opiniao);
		
		OpiniaoResponse opiniaoRes = new OpiniaoResponse(opiniao);
	
		URI uri = uriBuilder.path("/opiniao/{id}").buildAndExpand(opiniao.getId()).toUri();

		return ResponseEntity.created(uri).body(opiniaoRes);
		
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<OpiniaoResponse> detalhar  (@PathVariable Long id){
		
		Optional<Opiniao> opiniao = opiniaoRepository.findById(id);
		if(opiniao.isPresent()) {
			return ResponseEntity.ok(new OpiniaoResponse(opiniao.get()));
		}
		return ResponseEntity.notFound().build();
		
		
	}
	
	

}
