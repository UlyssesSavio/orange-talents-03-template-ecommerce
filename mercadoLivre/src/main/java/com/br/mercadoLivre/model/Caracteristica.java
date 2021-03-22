package com.br.mercadoLivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Caracteristica {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String atributo;
	@NotBlank
	private String valor;
	
	public Caracteristica() {
		
	}
	
	public Caracteristica(@NotBlank String atributo, @NotBlank String valor) {
		this.atributo = atributo;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public String getAtributo() {
		return atributo;
	}

	public String getValor() {
		return valor;
	}
	
	
	
		
	

}
