package com.br.mercadoLivre.enume;


import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;

import com.br.mercadoLivre.interfaces.RegraUriGateway;
import com.br.mercadoLivre.model.Compra;
import com.br.mercadoLivre.util.UriPagSeguro;
import com.br.mercadoLivre.util.UriPaypal;

public enum GatewayPagamento {
	
	pagseguro(new UriPagSeguro()),
	paypal(new UriPaypal());
	
	private RegraUriGateway regra;
	
	GatewayPagamento(RegraUriGateway regra) {
		this.regra = regra;
	}

	public RegraUriGateway getRegra() {
		return regra;
	}
	
	public URI geraUri(UriComponentsBuilder uriBuilder, Compra compra) {
		return regra.geraUri(uriBuilder, compra);
	}
	
	

}
