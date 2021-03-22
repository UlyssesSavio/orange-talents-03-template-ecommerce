package com.br.mercadoLivre.response;

import com.br.mercadoLivre.model.Usuario;

public class UsuarioResponse {
	
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public UsuarioResponse(String nome) {
		
		this.nome = nome;
	}
	
	
	public UsuarioResponse() {
		
	}

	public UsuarioResponse(Usuario usuario) {
		this.nome = usuario.getLogin();
	}

}
