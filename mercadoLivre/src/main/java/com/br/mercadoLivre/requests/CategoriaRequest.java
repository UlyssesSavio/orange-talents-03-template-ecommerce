package com.br.mercadoLivre.requests;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

import com.br.mercadoLivre.model.Categoria;
import com.br.mercadoLivre.repository.CategoriaRepository;
import com.br.mercadoLivre.validator.UniqueValue;

public class CategoriaRequest {
	
	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	
	private Long idMae;

	public CategoriaRequest() {
		
	}
	
	public CategoriaRequest(@NotBlank String nome, Long idMae) {
		
		this.nome = nome;
		this.idMae = idMae;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdMae() {
		return idMae;
	}
	
	
	public Categoria converter(CategoriaRepository categoriaRepository) {
	
		
		if(idMae != null) {
			Optional<Categoria> categoriaMae = null;
			categoriaMae = categoriaRepository.findById(idMae);
			Assert.state(categoriaMae.isPresent(), "Id de categoria mae invalido. Id:"+idMae);
			return new Categoria(nome, categoriaMae.get());
		}
		
		
		
		return new Categoria(nome, null);
	}
	
	
	

}
