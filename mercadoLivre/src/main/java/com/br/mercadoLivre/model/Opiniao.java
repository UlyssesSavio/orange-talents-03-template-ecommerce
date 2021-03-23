package com.br.mercadoLivre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Opiniao {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Min(1)@Max(5)
	@NotNull
	@Column(nullable = false)
	private int nota;
	@NotBlank
	@Column(nullable = false)
	private String titulo;
	@NotBlank
	@Column(nullable = false, length = 500)
	private String descricao;
	
	
	@NotNull
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Produto produto;
	
	public Opiniao() {
		
	}

	public Opiniao(@Min(1) @Max(5) @NotNull int nota, @NotBlank String titulo, @NotBlank String descricao,
			@NotNull Usuario usuario, Produto produto) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Long getId() {
		return id;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}
	
	

	
	
	
	
}
