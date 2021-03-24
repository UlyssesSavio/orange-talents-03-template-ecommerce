package com.br.mercadoLivre;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.mercadoLivre.enume.GatewayPagamento;
import com.br.mercadoLivre.model.Caracteristica;
import com.br.mercadoLivre.model.Categoria;
import com.br.mercadoLivre.model.Compra;
import com.br.mercadoLivre.model.Produto;
import com.br.mercadoLivre.model.Usuario;
import com.br.mercadoLivre.repository.ProdutoRepository;
import com.br.mercadoLivre.requests.CompraRequestTest;
import com.br.mercadoLivre.util.SenhaLimpa;

@SpringBootTest
public class TesteCompra {

	List<Caracteristica> caracteristicas;
	Categoria categoria;
	Usuario usuario;

	@Mock
	ProdutoRepository produtoRepository;

	@BeforeEach
	public void antes() {
		caracteristicas = List.of(new Caracteristica("cate1", "valor1"), new Caracteristica("cate2", "valor2"),
				new Caracteristica("cate3", "valor3"));

		categoria = new Categoria("categoria", null);
		usuario = new Usuario("email@teste.com", new SenhaLimpa("senhamaiorqueseis"));

	}

	@DisplayName("Verifica abate estoque")
	@ParameterizedTest
	@CsvSource({ "1,1,true", "1,2,false", "4,2,true", "1,5,false" })
	public void deveAbaterEstoque(int estoque, int quantidadePedida, boolean resultadoEsperado) {

		Produto produto = new Produto("nome", BigDecimal.valueOf(200.0), estoque, caracteristicas, "descricao",
				categoria, usuario);

		boolean resultado = produto.abateEstoque(quantidadePedida);

		Assertions.assertEquals(resultadoEsperado, resultado);

	}

	@Test
	@DisplayName("Deve aceitar compra")
	public void testeCompraAceita() {

		Produto produto = new Produto("nome", BigDecimal.valueOf(200.0), 10, caracteristicas, "descricao", categoria,
				usuario);

		Optional<Produto> produtoOp = Optional.of(produto);
		;
		Mockito.when(produtoRepository.findById(1L)).thenReturn(produtoOp);
		CompraRequestTest compraRequest = new CompraRequestTest(1L, 1, GatewayPagamento.pagseguro);
		Compra compra = compraRequest.converter(produtoRepository, usuario);

		Assertions.assertNotNull(compra);

	}
	
	@Test()
	@DisplayName("Deve dar erro de produto nao existe")
	public void testeProdutoCompraInvalido() {

		Produto produto = new Produto("nome", BigDecimal.valueOf(200.0), 10, caracteristicas, "descricao", categoria,
				usuario);

		Optional<Produto> produtoOp = Optional.of(produto);
		;
		Mockito.when(produtoRepository.findById(1L)).thenReturn(produtoOp);
		
		CompraRequestTest compraRequest = new CompraRequestTest(2L, 1, GatewayPagamento.pagseguro);
		Assertions.assertThrows(IllegalStateException.class, () ->{
			Compra compra = compraRequest.converter(produtoRepository, usuario);
		});
		
		

	}
	
	

}
