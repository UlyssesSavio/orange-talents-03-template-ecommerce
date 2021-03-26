package com.br.mercadoLivre.util;

import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;

import com.br.mercadoLivre.interfaces.RegraUriGateway;
import com.br.mercadoLivre.model.Compra;

public class UriPaypal implements RegraUriGateway{

	@Override
	public URI geraUri(UriComponentsBuilder uriBuilder, Compra compra) {
		
		return uriBuilder.path("/paypal/{id}").buildAndExpand(compra.getId()).toUri();
	}

}
