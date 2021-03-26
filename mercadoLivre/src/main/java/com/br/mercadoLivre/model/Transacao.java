package com.br.mercadoLivre.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.br.mercadoLivre.enume.StatusTransacao;
@Entity
public class Transacao {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String idTransacao;
	@NotNull
	private StatusTransacao status;
	
	
	@NotNull
	private LocalDateTime instante;
	
	@NotNull @Valid 
	@ManyToOne
	private Compra compra;
	
	
	public Transacao () {
		
	}
	
	public Transacao(@NotBlank String idTransacao, @NotNull StatusTransacao status, @NotNull @Valid Compra compra) {
		super();
		this.idTransacao = idTransacao;
		this.status = status;
		this.instante = LocalDateTime.now();
		this.compra = compra;
	}

	public String getIdTransacao() {
		return idTransacao;
	}

	public StatusTransacao getStatus() {
		return status;
	}

	public LocalDateTime getInstante() {
		return instante;
	}

	public boolean concluidaComSucesso() {
		return this.status.equals(StatusTransacao.sucesso);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTransacao == null) ? 0 : idTransacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (idTransacao == null) {
			if (other.idTransacao != null)
				return false;
		} else if (!idTransacao.equals(other.idTransacao))
			return false;
		return true;
	}

	
	
	
	

}
