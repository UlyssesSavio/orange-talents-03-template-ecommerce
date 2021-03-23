package com.br.mercadoLivre.requests;

import java.util.Optional;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.br.mercadoLivre.model.Opiniao;
import com.br.mercadoLivre.model.Produto;
import com.br.mercadoLivre.model.Usuario;
import com.br.mercadoLivre.repository.ProdutoRepository;

import io.jsonwebtoken.lang.Assert;

public class OpiniaoRequest {
	
	@Min(1)@Max(5)
	@NotNull
	@Column(nullable = false)
	private int nota;
	@NotBlank
	@Column(nullable = false)
	private String titulo;
	@NotBlank
	@Column(nullable = false, length = 500)
	@Length(max=500)
	private String descricao;
	
	
	@NotNull
	private Long idProduto;
	
	
	public OpiniaoRequest() {
		
	}


	public OpiniaoRequest(@Min(1) @Max(5) @NotNull int nota, @NotBlank String titulo,
			@NotBlank @Length(max = 500) String descricao,  @NotNull Long idProduto) {
		
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.idProduto = idProduto;
	}


	public int getNota() {
		return nota;
	}


	public String getTitulo() {
		return titulo;
	}


	public String getDescricao() {
		return descricao;
	}


	


	public Long getIdProduto() {
		return idProduto;
	}
	
	public Opiniao converter(ProdutoRepository produtoRepository) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = (Usuario)authentication.getPrincipal();
		
		Optional<Produto> produtoOp = produtoRepository.findById(idProduto);
		
		Assert.state(produtoOp.isPresent(), "Id de produto invalido.");
		
		
		
		return new Opiniao(nota, titulo, descricao, usuario, produtoOp.get());
		
	}
	

}
