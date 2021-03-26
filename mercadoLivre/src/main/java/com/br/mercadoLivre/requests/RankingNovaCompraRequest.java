package com.br.mercadoLivre.requests;

import javax.validation.constraints.NotNull;

public class RankingNovaCompraRequest {
	private Long idCompra;
	@NotNull
	private Long idDonoProduto;
	
	public RankingNovaCompraRequest() {
		
	}

	public RankingNovaCompraRequest(Long idCompra, Long idDonoProduto) {
		super();
		this.idCompra = idCompra;
		this.idDonoProduto = idDonoProduto;
	}
	
	

	public Long getIdCompra() {
		return idCompra;
	}

	public Long getIdDonoProduto() {
		return idDonoProduto;
	}

	@Override
	public String toString() {
		return "NovaCompraNFRequest [idCompra=" + idCompra + ", idComprador="
				+ idDonoProduto + "]";
	}
}
