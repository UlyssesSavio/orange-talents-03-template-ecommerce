package com.br.mercadoLivre.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Produto {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable=false)
	private String nome;
	
	@NotNull
	@Min(1)
	private BigDecimal valor;
	
	@NotNull
	@Min(1)
	private int quantidade;
	
	
	//Utilizado o cascade para inserir as caracteristica antes de atribuir no produto
	@ManyToMany(cascade=CascadeType.PERSIST)
	private List<Caracteristica> caracteristicas = new ArrayList<>();;
	
	@NotEmpty
	@Column(length=1000, nullable=false)
	private String descricao;
	
	
	@NotNull
	@ManyToOne
	private Categoria categoria;

	
	public Produto() {
		
	}

	




	public Produto(@NotBlank String nome, @NotNull @Min(1) BigDecimal valor, @NotNull @Min(1) int quantidade,
			List<Caracteristica> caracteristicas, @NotEmpty String descricao, @NotNull Categoria categoria) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicas;
		this.descricao = descricao;
		this.categoria = categoria;
	}






	public Long getId() {
		return id;
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

	public Categoria getCategoria() {
		return categoria;
	}
	
	
	
	
	

}
