package com.br.mercadoLivre.requests;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.context.annotation.Profile;

import com.br.mercadoLivre.enume.GatewayPagamento;
import com.br.mercadoLivre.model.Compra;
import com.br.mercadoLivre.model.Produto;
import com.br.mercadoLivre.model.Usuario;
import com.br.mercadoLivre.repository.ProdutoRepository;

import io.jsonwebtoken.lang.Assert;

@Profile("tests")
public class CompraRequestTest {

	@NotNull
	private Long idProduto;
	@NotNull
	@PositiveOrZero
	private int quantidade = 0;

	@NotNull
	@Valid
	private GatewayPagamento gateway;

	public CompraRequestTest() {

	}

	public CompraRequestTest(@NotNull Long idProduto, @NotNull @Positive int quantidade,
			@NotNull @Valid GatewayPagamento gateway) {
		this.idProduto = idProduto;
		this.quantidade = quantidade;
		this.gateway = gateway;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}

	public Compra converter(ProdutoRepository produtoRepository, Usuario usuario) {

		Optional<Produto> produtoOp = produtoRepository.findById(idProduto);

		Assert.state(produtoOp.isPresent(), "Id de produto invalido.");

		Produto produto = produtoOp.get();

		Assert.state(produto.abateEstoque(quantidade),
				"Estoque indisponivel, quantidade disponivel: " + produto.getQuantidade());

		produtoRepository.save(produto);

		return new Compra(usuario, produto, quantidade, gateway);
	}

}
