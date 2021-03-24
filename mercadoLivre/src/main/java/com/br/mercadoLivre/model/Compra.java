package com.br.mercadoLivre.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.br.mercadoLivre.enume.GatewayPagamento;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	private Usuario usuario;

	@NotNull
	@ManyToOne
	private Produto produto;

	@NotNull
	@PositiveOrZero
	private int quantidade = 0;

	@NotNull
	@Column(nullable = false)
	private BigDecimal valorNoMomento;

	@NotNull
	@Valid
	private GatewayPagamento gateway;

	@NotNull
	private LocalDate dataCompra = LocalDate.now();;

	public Compra() {

	}

	public Compra(@NotNull Usuario usuario, @NotNull Produto produto, @NotNull @PositiveOrZero int quantidade,
			@NotNull @Valid GatewayPagamento gateway) {
		super();
		this.usuario = usuario;
		this.produto = produto;
		this.quantidade = quantidade;
		this.gateway = gateway;

		this.valorNoMomento = produto.getValor().multiply(BigDecimal.valueOf(quantidade));
		this.dataCompra = LocalDate.now();
	}

	public Long getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public BigDecimal getValorNoMomento() {
		return valorNoMomento;
	}

	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}

}
