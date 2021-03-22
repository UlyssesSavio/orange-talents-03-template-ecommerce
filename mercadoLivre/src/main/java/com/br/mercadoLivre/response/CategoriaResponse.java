package com.br.mercadoLivre.response;

import com.br.mercadoLivre.model.Categoria;

public class CategoriaResponse {
	
	
	private String nome;
	private Categoria categoriaMae;
	
	public CategoriaResponse() {
		
	}
	
	public CategoriaResponse(String nome, Categoria categoriaMae) {
		
		this.nome = nome;
		this.categoriaMae = categoriaMae;
	}
	public CategoriaResponse(Categoria categoria) {
		this.nome = categoria.getNome();
		this.categoriaMae = categoria.getCategoriaMae();
	}

	public String getNome() {
		return nome;
	}
	public Categoria getCategoriaMae() {
		return categoriaMae;
	}
	
	
	
	
	

}
