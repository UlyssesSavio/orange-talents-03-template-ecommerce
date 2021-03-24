package com.br.mercadoLivre.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.mercadoLivre.enume.GatewayPagamento;
import com.br.mercadoLivre.model.Compra;
import com.br.mercadoLivre.repository.CompraRepository;
import com.br.mercadoLivre.repository.ProdutoRepository;
import com.br.mercadoLivre.requests.CompraRequest;
import com.br.mercadoLivre.response.CompraResponse;

@RestController
@RequestMapping("/compra")
public class FechamentoCompraController {

	private CompraRepository compraRepository;
	private ProdutoRepository produtoRepository;

	public FechamentoCompraController(CompraRepository compraRepository, ProdutoRepository produtoRepository) {
		this.compraRepository = compraRepository;
		this.produtoRepository = produtoRepository;
	}

	@Transactional
	@PostMapping
	public ResponseEntity<CompraResponse> realizaCompra(@RequestBody @Valid CompraRequest compraRequest,
			UriComponentsBuilder uriBuilder) {

		Compra compra = compraRequest.converter(produtoRepository);

		compraRepository.save(compra);
		CompraResponse compraResponse = new CompraResponse(compra);
		envioDeEmail(compra);
		if (compra.getGateway().equals(GatewayPagamento.pagseguro)) {

			URI uri = uriBuilder.path("/pagseguro/{id}").buildAndExpand(compra.getId()).toUri();

			return ResponseEntity.created(uri).body(compraResponse);

		} else {

			URI uri = uriBuilder.path("/paypal/{id}").buildAndExpand(compra.getId()).toUri();
			return ResponseEntity.created(uri).body(compraResponse);

		}

	}

	private void envioDeEmail(Compra compra) {

		System.out.println("\n\n\nEnvio de email 2.0 \n\n\n");
		System.out.println("Remetente:" + compra.getUsuario().getLogin());
		System.out.println("Destinario:" + compra.getProduto().getUsuario().getLogin());
		System.out
				.println("\nMensagem: Comprei " + compra.getQuantidade() + " do seu " + compra.getProduto().getNome());
		System.out.println("\n\n att,\n " + compra.getUsuario().getLogin() + "\n\n");

	}

}
