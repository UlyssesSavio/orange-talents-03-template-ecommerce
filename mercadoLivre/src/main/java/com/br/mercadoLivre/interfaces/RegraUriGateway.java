package com.br.mercadoLivre.interfaces;

import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;

import com.br.mercadoLivre.model.Compra;

public interface RegraUriGateway {
	
	public URI geraUri(UriComponentsBuilder uriBuilder, Compra compra);

}
