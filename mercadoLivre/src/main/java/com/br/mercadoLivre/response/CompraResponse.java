package com.br.mercadoLivre.response;

import java.math.BigDecimal;

import com.br.mercadoLivre.enume.GatewayPagamento;
import com.br.mercadoLivre.model.Compra;

public class CompraResponse {

	private UsuarioResponse usuario;
	private ProdutoResponse produto;
	private int quantidade = 0;
	private BigDecimal valorMomento;
	private GatewayPagamento gateway;

	public CompraResponse() {

	}

	public CompraResponse(Compra compra) {
		this.usuario = new UsuarioResponse(compra.getUsuario());
		this.produto = new ProdutoResponse(compra.getProduto());
		this.quantidade = compra.getQuantidade();
		this.valorMomento = compra.getValorNoMomento();
		this.gateway = compra.getGateway();
	}

	public UsuarioResponse getUsuario() {
		return usuario;
	}

	public ProdutoResponse getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public BigDecimal getValorMomento() {
		return valorMomento;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}
	

}
