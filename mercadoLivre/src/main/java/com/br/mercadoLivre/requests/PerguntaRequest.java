package com.br.mercadoLivre.requests;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.br.mercadoLivre.model.Pergunta;
import com.br.mercadoLivre.model.Produto;
import com.br.mercadoLivre.model.Usuario;
import com.br.mercadoLivre.repository.ProdutoRepository;

import io.jsonwebtoken.lang.Assert;

public class PerguntaRequest {
	
	@NotBlank
	private String titulo;
	
	@NotNull
	private Long idProduto;
	
	public PerguntaRequest() {
		
	}

	public PerguntaRequest(@NotBlank String titulo, @NotNull Long idProduto) {
		this.titulo = titulo;
		this.idProduto = idProduto;
	}

	public String getTitulo() {
		return titulo;
	}

	public Long getIdProduto() {
		return idProduto;
	}
	
	public Pergunta converter(ProdutoRepository produtoRepository) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = (Usuario)authentication.getPrincipal();
		
		Optional<Produto> produtoOp = produtoRepository.findById(idProduto);
		
		Assert.state(produtoOp.isPresent(), "Id de produto invalido.");
		
		return new Pergunta(titulo, usuario, produtoOp.get());
	}
	

}
