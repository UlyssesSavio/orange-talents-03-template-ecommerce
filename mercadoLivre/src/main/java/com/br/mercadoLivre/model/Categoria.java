package com.br.mercadoLivre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	@Column(unique = true, nullable=false)
	private String nome;
	
	
	@ManyToOne
	private Categoria categoriaMae;


	public Categoria() {
		
	}
	
	public Categoria(@NotBlank String nome, Categoria categoriaMae) {
		this.nome = nome;
		this.categoriaMae = categoriaMae;
	}
	


	public Long getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}


	public Categoria getCategoriaMae() {
		return categoriaMae;
	}

	
	
	
	
}
