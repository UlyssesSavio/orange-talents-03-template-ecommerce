package com.br.mercadoLivre.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



import com.br.mercadoLivre.model.Caracteristica;
import com.br.mercadoLivre.model.Categoria;
import com.br.mercadoLivre.model.Imagem;
import com.br.mercadoLivre.model.Produto;

public class ProdutoResponse {

	private String nome;
	private BigDecimal valor;
	private int quantidade;
	private List<Caracteristica> caracteristicas = new ArrayList<>();;
	private String descricao;
	private Categoria categoria;
	private UsuarioResponse usuario;
	
	private List<Imagem> imagens = new ArrayList<Imagem>();

	public ProdutoResponse() {

	}

	public ProdutoResponse(String nome, BigDecimal valor, int quantidade, List<Caracteristica> caracteristicas,
			String descricao, Categoria categoria) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicas;
		this.descricao = descricao;
		this.categoria = categoria;
	}
	
	
	

	public ProdutoResponse(String nome, BigDecimal valor, int quantidade, List<Caracteristica> caracteristicas,
			String descricao, Categoria categoria, UsuarioResponse usuario, List<Imagem> imagens) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicas;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
		this.imagens = imagens;
	}

	public ProdutoResponse(Produto produto) {
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.quantidade = produto.getQuantidade();
		this.caracteristicas = produto.getCaracteristicas();
		this.descricao = produto.getDescricao();
		this.categoria = produto.getCategoria();
		this.usuario = new UsuarioResponse(produto.getUsuario());
		this.imagens = produto.getImagens();
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

	public UsuarioResponse getUsuario() {
		return usuario;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	
	

	
	
	
}
