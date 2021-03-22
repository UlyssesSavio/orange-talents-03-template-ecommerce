package com.br.mercadoLivre.util;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class SenhaLimpa {
	
	private String senha;

	public SenhaLimpa(@NotBlank @Length(min=6) String senha) {
		this.senha = senha;
		Assert.isTrue(StringUtils.hasLength(senha),"Nao pode ser em branco");
		Assert.isTrue(senha.length() >= 6,"Nao pode ser menor que 6");
		
	}
	
	public String hash() {
		return new BCryptPasswordEncoder().encode(senha);
	}
	
	

}
