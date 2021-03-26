package com.br.mercadoLivre;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.mercadoLivre.enume.GatewayPagamento;
import com.br.mercadoLivre.enume.StatusRetornoPagSeguro;
import com.br.mercadoLivre.model.Caracteristica;
import com.br.mercadoLivre.model.Categoria;
import com.br.mercadoLivre.model.Compra;
import com.br.mercadoLivre.model.Produto;
import com.br.mercadoLivre.model.Transacao;
import com.br.mercadoLivre.model.Usuario;
import com.br.mercadoLivre.repository.CompraRepository;
import com.br.mercadoLivre.repository.ProdutoRepository;
import com.br.mercadoLivre.requests.CompraRequestTest;
import com.br.mercadoLivre.requests.PagSeguroRequest;
import com.br.mercadoLivre.util.SenhaLimpa;

@SpringBootTest
public class TesteFechaCompra2 {

	List<Caracteristica> caracteristicas;
	Categoria categoria;
	Usuario usuario;
	Produto produto;
	Compra compra;

	@Mock
	ProdutoRepository produtoRepository;

	@Mock
	CompraRepository compraRepository;

	@BeforeEach
	public void antes() {
		caracteristicas = List.of(new Caracteristica("cate1", "valor1"), new Caracteristica("cate2", "valor2"),
				new Caracteristica("cate3", "valor3"));

		categoria = new Categoria("categoria", null);
		usuario = new Usuario("email@teste.com", new SenhaLimpa("senhamaiorqueseis"));

		produto = new Produto("nome", BigDecimal.valueOf(200.0), 10, caracteristicas, "descricao", categoria, usuario);

	}

	@Test
	@DisplayName("Deve dar compra nao encontrada")
	public void deveDarCompraNaoEncontrada() {
		Optional<Produto> produtoOp = Optional.of(produto);
		Mockito.when(produtoRepository.findById(1L)).thenReturn(produtoOp);

		CompraRequestTest compraTest = new CompraRequestTest(1L, 2, GatewayPagamento.pagseguro);
		Compra compra = compraTest.converter(produtoRepository, usuario);

		Optional<Compra> compraOp = Optional.of(compra);

		Mockito.when(compraRepository.findById(1L)).thenReturn(compraOp);

		Optional<Compra> compraErrada = compraRepository.findById(2L);

		Assertions.assertFalse(compraErrada.isPresent());

	}

	@Test
	@DisplayName("Deve dar compra encontrada")
	public void deveDarCompraEncontrada() {
		Optional<Produto> produtoOp = Optional.of(produto);
		Mockito.when(produtoRepository.findById(1L)).thenReturn(produtoOp);

		CompraRequestTest compraTest = new CompraRequestTest(1L, 2, GatewayPagamento.pagseguro);
		Compra compra = compraTest.converter(produtoRepository, usuario);

		Optional<Compra> compraOp = Optional.of(compra);

		Mockito.when(compraRepository.findById(1L)).thenReturn(compraOp);

		Optional<Compra> compraCerta = compraRepository.findById(1L);

		Assertions.assertTrue(compraCerta.isPresent());

	}

	@Test
	@DisplayName("Deve cadastrar transacao")
	public void deveCadastrarTransacao() {

		Optional<Produto> produtoOp = Optional.of(produto);
		Mockito.when(produtoRepository.findById(1L)).thenReturn(produtoOp);

		CompraRequestTest compraTest = new CompraRequestTest(1L, 2, GatewayPagamento.pagseguro);
		Compra compra = compraTest.converter(produtoRepository, usuario);

		PagSeguroRequest pagSeguroResquest = new PagSeguroRequest("123", StatusRetornoPagSeguro.SUCESSO);
		compra.adicionaTransacao(pagSeguroResquest);

		Transacao novaTransacao = pagSeguroResquest.converter(compra);

		Assertions.assertTrue(compra.getTransacoes().contains(novaTransacao));

	}

	@Test
	@DisplayName("Deve dar transacao Cadastrada")
	public void deveDarTransacaoCadastrada() {

		Optional<Produto> produtoOp = Optional.of(produto);
		Mockito.when(produtoRepository.findById(1L)).thenReturn(produtoOp);

		CompraRequestTest compraTest = new CompraRequestTest(1L, 2, GatewayPagamento.pagseguro);
		Compra compra = compraTest.converter(produtoRepository, usuario);

		PagSeguroRequest pagSeguroResquest = new PagSeguroRequest("123", StatusRetornoPagSeguro.SUCESSO);
		compra.adicionaTransacao(pagSeguroResquest);

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			compra.adicionaTransacao(pagSeguroResquest);
		});

	}
	
	@Test
	@DisplayName("Deve dar transacao concluida")
	public void deveDarTransacaoConcluida() {

		Optional<Produto> produtoOp = Optional.of(produto);
		Mockito.when(produtoRepository.findById(1L)).thenReturn(produtoOp);

		CompraRequestTest compraTest = new CompraRequestTest(1L, 2, GatewayPagamento.pagseguro);
		Compra compra = compraTest.converter(produtoRepository, usuario);

		PagSeguroRequest pagSeguroResquest = new PagSeguroRequest("123", StatusRetornoPagSeguro.SUCESSO);
		compra.adicionaTransacao(pagSeguroResquest);
		
		PagSeguroRequest pagSeguroResquest2 = new PagSeguroRequest("1234", StatusRetornoPagSeguro.ERRO);

		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			compra.adicionaTransacao(pagSeguroResquest2);
		});

	}

}
