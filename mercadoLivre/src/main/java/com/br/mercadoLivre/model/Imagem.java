package com.br.mercadoLivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Imagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String link = new String();

	public Imagem() {

	}

	public Imagem(String link) {

		this.link = link;
	}

	public Long getId() {
		return id;
	}

	public String getLink() {
		return link;
	}

}
