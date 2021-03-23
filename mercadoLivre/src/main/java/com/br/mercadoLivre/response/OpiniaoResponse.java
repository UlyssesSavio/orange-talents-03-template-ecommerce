package com.br.mercadoLivre.response;

import com.br.mercadoLivre.model.Opiniao;
public class OpiniaoResponse {

	private int nota;
	private String titulo;
	private String descricao;
	private UsuarioResponse usuario;
	private ProdutoResponse produto;

	public OpiniaoResponse() {

	}

	
	public OpiniaoResponse(Opiniao opiniao) {
	
		this.nota = opiniao.getNota();
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
		this.usuario = new UsuarioResponse(opiniao.getUsuario());
		this.produto = new ProdutoResponse(opiniao.getProduto());
	}
	
	
	public OpiniaoResponse(int nota, String titulo, String descricao, UsuarioResponse usuario,
			ProdutoResponse produto) {
	
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
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


	public UsuarioResponse getUsuario() {
		return usuario;
	}


	public ProdutoResponse getProduto() {
		return produto;
	}

	

	

}
