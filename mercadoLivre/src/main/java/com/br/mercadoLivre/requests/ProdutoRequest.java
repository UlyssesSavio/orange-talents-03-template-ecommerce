package com.br.mercadoLivre.requests;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.br.mercadoLivre.model.Caracteristica;
import com.br.mercadoLivre.model.Categoria;
import com.br.mercadoLivre.model.Produto;
import com.br.mercadoLivre.repository.CaracteristicaRepository;
import com.br.mercadoLivre.repository.CategoriaRepository;

import io.jsonwebtoken.lang.Assert;

public class ProdutoRequest {

	@NotBlank
	@Column(nullable = false)
	private String nome;

	@NotNull

	@Min(1)
	private BigDecimal valor;

	@NotNull
	@Min(1)
	private int quantidade;

	private List<Caracteristica> caracteristicas = new ArrayList<>();;

	@NotEmpty
	@Column(length = 1000, nullable = false)
	private String descricao;

	@NotNull
	private Long idCategoria;

	public ProdutoRequest() {

	}

	




	public ProdutoRequest(@NotBlank String nome, @NotNull @Min(1) BigDecimal valor, @NotNull @Min(1) int quantidade,
			List<Caracteristica> caracteristicas, @NotEmpty String descricao, @NotNull Long idCategoria) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicas;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
	}






	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}
	
	

	public Long getIdCategoria() {
		return idCategoria;
	}






	public Produto converter(CategoriaRepository categoriaRepository) {

		Assert.state(caracteristicas.size() >= 3, "Precisa ter 3 ou mais caracteristicas");

		Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);

		Assert.state(categoria.isPresent(), "Categoria nao encontrada. Id:" + idCategoria);

		return new Produto(nome, valor, quantidade, caracteristicas, descricao, categoria.get());
	}

}
