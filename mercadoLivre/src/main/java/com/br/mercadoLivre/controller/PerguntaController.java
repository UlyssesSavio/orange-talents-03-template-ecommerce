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

import com.br.mercadoLivre.model.Pergunta;
import com.br.mercadoLivre.repository.PerguntaRepository;
import com.br.mercadoLivre.repository.ProdutoRepository;
import com.br.mercadoLivre.requests.PerguntaRequest;
import com.br.mercadoLivre.response.PerguntaResponse;

@RestController
@RequestMapping("/pergunta")
public class PerguntaController {
	
	private PerguntaRepository perguntaRepository;
	private ProdutoRepository produtoRepository;
	
	

	public PerguntaController(PerguntaRepository perguntaRepository, ProdutoRepository produtoRepository) {
		super();
		this.perguntaRepository = perguntaRepository;
		this.produtoRepository = produtoRepository;
	}

	@Transactional
	@PostMapping
	private ResponseEntity<PerguntaResponse> cadastrar (@RequestBody @Valid PerguntaRequest perguntaRequest, UriComponentsBuilder uriBuilder){
		
		Pergunta pergunta = perguntaRequest.converter(produtoRepository);
		
		perguntaRepository.save(pergunta);
		
		PerguntaResponse perguntaRes = new PerguntaResponse(pergunta);
	
		URI uri = uriBuilder.path("/pergunta/{id}").buildAndExpand(pergunta.getId()).toUri();
		
		enviarEmail(pergunta);

		return ResponseEntity.created(uri).body(perguntaRes);
		
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<PerguntaResponse> detalhar  (@PathVariable Long id){
		
		Optional<Pergunta> pergunta = perguntaRepository.findById(id);
		if(pergunta.isPresent()) {
			return ResponseEntity.ok(new PerguntaResponse(pergunta.get()));
		}
		return ResponseEntity.notFound().build();
		
		
	}
	
	private void enviarEmail(Pergunta pergunta) {
		
		System.out.println("\n\n Enviando email ...... \n\n");
		System.out.println("\n\n Remetente: "+pergunta.getUsuario().getLogin());
		System.out.println("Destinario: "+pergunta.getProduto().getUsuario().getLogin());
		System.out.println("\n Mensagem:\n"+pergunta.getTitulo());
		System.out.println("\n Data Criacao:\n"+pergunta.getInstanteCriacao());
		System.out.println("\n\n");
	}
	

}
