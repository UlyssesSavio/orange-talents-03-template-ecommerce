package com.br.mercadoLivre.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.mercadoLivre.model.Produto;
import com.br.mercadoLivre.model.Usuario;
import com.br.mercadoLivre.repository.CategoriaRepository;
import com.br.mercadoLivre.repository.ImagemRepository;
import com.br.mercadoLivre.repository.ProdutoRepository;
import com.br.mercadoLivre.requests.ImagensRequest;
import com.br.mercadoLivre.requests.ProdutoRequest;
import com.br.mercadoLivre.response.ErroDeFormularioResponse;
import com.br.mercadoLivre.response.ProdutoResponse;
import com.br.mercadoLivre.util.UploadFotoFake;

import io.jsonwebtoken.lang.Assert;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	private ProdutoRepository produtoRepository;
	private CategoriaRepository categoriaRepository;
	private ImagemRepository imagemRepository;

	public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository,
			ImagemRepository imagemRepository) {

		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;
		this.imagemRepository = imagemRepository;
	}

	@Transactional
	@PostMapping
	private ResponseEntity<ProdutoResponse> cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest,
			UriComponentsBuilder uriBuilder) {

		Produto produto = produtoRequest.converter(categoriaRepository);

		produtoRepository.save(produto);

		ProdutoResponse produtoRes = new ProdutoResponse(produto);

		URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();

		return ResponseEntity.created(uri).body(produtoRes);

	}

	@GetMapping("/{id}")
	private ResponseEntity<ProdutoResponse> detalhar(@PathVariable Long id) {

		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			return ResponseEntity.ok(new ProdutoResponse(produto.get()));
		}
		return ResponseEntity.notFound().build();

	}

	@Transactional
	@PostMapping("/{id}/imagens")
	private ResponseEntity<ProdutoResponse> cadastrarImagens(@PathVariable("id") Long id,
			@RequestParam("imagens") MultipartFile[] imagensRequest) {

		

		UploadFotoFake up = new UploadFotoFake(imagensRequest, id);

		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isPresent()) {

			Produto prod = produto.get();

			prod.associaImagens(up.getListaLinks());

			prod.teste();

			imagemRepository.saveAll(prod.getImagens());

			produtoRepository.save(prod);

			return ResponseEntity.ok(new ProdutoResponse(prod));
		}

		return ResponseEntity.notFound().build();
	}

}
