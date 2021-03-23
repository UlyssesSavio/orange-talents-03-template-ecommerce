package com.br.mercadoLivre.response;

import java.time.LocalDate;

import com.br.mercadoLivre.model.Pergunta;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PerguntaResponse {

	private String titulo;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate instanteCriacao;

	private UsuarioResponse usuario;
	private ProdutoResponse produto;

	public PerguntaResponse() {

	}

	public PerguntaResponse(Pergunta pergunta) {
		this.titulo = pergunta.getTitulo();
		this.instanteCriacao = pergunta.getInstanteCriacao();
		this.usuario = new UsuarioResponse(pergunta.getUsuario());
		this.produto = new ProdutoResponse(pergunta.getProduto());
	}

	public PerguntaResponse(String titulo, LocalDate instanteCriacao, UsuarioResponse usuario,
			ProdutoResponse produto) {
		this.titulo = titulo;
		this.instanteCriacao = instanteCriacao;
		this.usuario = usuario;
		this.produto = produto;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDate getInstanteCriacao() {
		return instanteCriacao;
	}

	public UsuarioResponse getUsuario() {
		return usuario;
	}

	public ProdutoResponse getProduto() {
		return produto;
	}

}
