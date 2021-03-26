package com.br.mercadoLivre.enume;

public enum StatusRetornoPagSeguro {
	
	ERRO, SUCESSO;

	public StatusTransacao normaliza() {
		if(this == StatusRetornoPagSeguro.ERRO) {
			return StatusTransacao.erro;
		}else {
			return StatusTransacao.sucesso;
		}
	}

}
