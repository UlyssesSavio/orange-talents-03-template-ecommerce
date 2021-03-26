package com.br.mercadoLivre.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.mercadoLivre.enume.GatewayPagamento;
import com.br.mercadoLivre.interfaces.Pagamento;
import com.br.mercadoLivre.model.Compra;
import com.br.mercadoLivre.repository.CompraRepository;
import com.br.mercadoLivre.requests.PagSeguroRequest;
import com.br.mercadoLivre.requests.PayPalRequest;
import com.br.mercadoLivre.service.EventosNovaCompra;

import io.jsonwebtoken.lang.Assert;

@RestController
public class FechamentoCompraParte2Controller {

	private CompraRepository compraRepository;
	
	@Autowired
	private EventosNovaCompra eventosNovaCompra;

	

	

	public FechamentoCompraParte2Controller(CompraRepository compraRepository) {
		super();
		this.compraRepository = compraRepository;
	}

	@PostMapping(path = "/pagseguro/{id}")
	@Transactional
	public String compraPagseguro(@PathVariable Long id, @Valid @RequestBody PagSeguroRequest pagSeguroRequest) {

		Optional<Compra> compraOp = compraRepository.findById(id);

		Assert.state(compraOp.isPresent(), "Id de compra invalido.");

		Compra compra = compraOp.get();

		validaGateway(compra, GatewayPagamento.pagseguro);

		compra.adicionaTransacao(pagSeguroRequest);

		compraRepository.save(compra);
		enviarEmail(compra, pagSeguroRequest);
		
		eventosNovaCompra.processa(compra);

		return compra.toString();	
		
	}

	@PostMapping(path = "/paypal/{id}")
	@Transactional
	public String compraPaypal(@PathVariable Long id, @Valid @RequestBody PayPalRequest payPalRequest) {

		Optional<Compra> compraOp = compraRepository.findById(id);
		Assert.state(compraOp.isPresent(), "Id de compra invalido.");
		Compra compra = compraOp.get();

		validaGateway(compra, GatewayPagamento.paypal);

		compra.adicionaTransacao(payPalRequest);

		compraRepository.save(compra);

		enviarEmail(compra, payPalRequest);
		
		eventosNovaCompra.processa(compra);
		
		return compra.toString();	
		}
	
	
	private void enviarEmail(Compra compra, Pagamento pag) {
		
		System.out.println("\n\n\n\n----------------------------Enviandor de email 3.0---------------------------\n\n\n");
		System.out.println("Remetente:"+compra.getUsuario().getLogin());
		System.out.println("Destinario:"+compra.getDonoProduto().getLogin());
		System.out.println("\nMensagem:Compra de "+compra.getQuantidade()+" do seu "+compra.getProduto().getNome()+".");
		System.out.println("Deu um total de RS:"+compra.getValorNoMomento()+".");
		System.out.println("Codigo de transacao:"+pag.retornaIdTransacao());
		System.out.println("Status:"+pag.retornaStatusString());
		System.out.println("Gateway:"+pag.retornaNomeGateway());
		System.out.println("--------------------------------------------------------------------------------------\n\n");
	}
	
	
	private void validaGateway(Compra compra, GatewayPagamento gatewayCorreto) {

		Assert.state(compra.getGateway().equals(gatewayCorreto),
				"Pagamento nao e realizado por esse gateway, gateway correto:" + compra.getGateway());

	}

}
