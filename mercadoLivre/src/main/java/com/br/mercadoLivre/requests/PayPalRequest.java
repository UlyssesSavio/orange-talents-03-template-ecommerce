package com.br.mercadoLivre.requests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.br.mercadoLivre.enume.StatusTransacao;
import com.br.mercadoLivre.interfaces.Pagamento;
import com.br.mercadoLivre.model.Compra;
import com.br.mercadoLivre.model.Transacao;

public class PayPalRequest implements Pagamento {

	@NotBlank
	private String idTransacao;

	@NotNull
	@Min(0)
	@Max(1)
	private int status = 0;

	public PayPalRequest() {

	}

	public PayPalRequest(@NotBlank String idTransacao, @NotNull int status) {
		super();
		this.idTransacao = idTransacao;
		this.status = status;
	}

	public String getIdTransacao() {
		return idTransacao;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public Transacao converter(Compra compra) {

		return new Transacao(idTransacao, normalizaStatus(), compra);
	}

	private StatusTransacao normalizaStatus() {
		if (status == 0) {
			return StatusTransacao.erro;
		} else {
			return StatusTransacao.sucesso;
		}
	}

	@Override
	public String toString() {
		return "PayPalRequest [idTransacao=" + idTransacao + ", status=" + status + "]";
	}
	
	@Override
	public String retornaIdTransacao() {
		
		return idTransacao;
	}


	@Override
	public String retornaStatusString() {
		
		return status+"";
	}

	@Override
	public String retornaNomeGateway() {
		
		return "PayPal";
	}

}
