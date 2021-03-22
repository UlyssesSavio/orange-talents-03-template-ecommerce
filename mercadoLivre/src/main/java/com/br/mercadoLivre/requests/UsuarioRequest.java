package com.br.mercadoLivre.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import org.springframework.util.StringUtils;
import com.br.mercadoLivre.model.Usuario;
import com.br.mercadoLivre.util.SenhaLimpa;
import com.br.mercadoLivre.validator.UniqueValue;


public class UsuarioRequest {

	@NotBlank
	@Email
	@UniqueValue(domainClass = Usuario.class, fieldName = "login")
	private String login;
	@NotBlank
	@Length(min=6)
	private String senha;
	
	public UsuarioRequest() {
		
	}
	
	
	
	public UsuarioRequest(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha) {
		Assert.isTrue(StringUtils.hasLength(login),"Nao pode ser em branco");
		Assert.isTrue(StringUtils.hasLength(senha),"Nao pode ser em branco");
		Assert.isTrue(senha.length() >= 6,"Nao pode ser menor que 6");

		
		this.login = login;
		this.senha = senha;
	}
	public String getLogin() {
		return login;
	}
	public String getSenha() {
		return senha;
	}
	
	public Usuario converter() {
		return new Usuario(login, new SenhaLimpa(senha));
	}
	
	
	
}
