package com.br.mercadoLivre.interfaces;

import com.br.mercadoLivre.model.Compra;
import com.br.mercadoLivre.model.Transacao;

public interface Pagamento {
	
	public Transacao converter(Compra compra);
	
	public String retornaIdTransacao();
	public String retornaStatusString();

	public String retornaNomeGateway();

}
