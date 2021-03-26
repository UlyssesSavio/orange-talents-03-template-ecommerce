package com.br.mercadoLivre.requests;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.br.mercadoLivre.enume.StatusRetornoPagSeguro;
import com.br.mercadoLivre.interfaces.Pagamento;
import com.br.mercadoLivre.model.Compra;
import com.br.mercadoLivre.model.Transacao;

public class PagSeguroRequest implements Pagamento{

	@NotBlank
	private String idTransacao;
	
	@NotNull
	@Valid
	private StatusRetornoPagSeguro status;

	public PagSeguroRequest() {
		
	}
	
	
	public PagSeguroRequest(@NotBlank String idTransacao, @NotNull @Valid StatusRetornoPagSeguro status) {
		super();
		this.idTransacao = idTransacao;
		this.status = status;
	}
	
	

	@Override
	public String toString() {
		return "PagSeguroRequest [idTransacao=" + idTransacao + ", status=" + status + "]";
	}


	public String getIdTransacao() {
		return idTransacao;
	}

	public StatusRetornoPagSeguro getStatus() {
		return status;
	}


	

	@Override
	public Transacao converter(Compra compra) {
		return new Transacao(idTransacao, status.normaliza(), compra);
	}


	@Override
	public String retornaIdTransacao() {
		
		return idTransacao;
	}


	@Override
	public String retornaStatusString() {
		
		return status.toString();
	}


	@Override
	public String retornaNomeGateway() {
		
		return "PagSeguro";
	}
	
	
	
}
