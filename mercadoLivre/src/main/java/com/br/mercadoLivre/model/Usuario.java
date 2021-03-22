package com.br.mercadoLivre.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.br.mercadoLivre.util.SenhaLimpa;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Usuario {

	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	@Email
	private String login;
	@NotBlank
	@Length(min=6)
	private String senha;
	
	@NotNull
	@PastOrPresent
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInstante;

	
	
	public Usuario() {
		
	}
	
	
	public Usuario(@NotBlank @Email String login, @NotNull @Valid SenhaLimpa senhaLimpa) {
		
		this.login = login;
		this.senha = senhaLimpa.hash();
		this.dataInstante = LocalDate.now();
		
		System.out.println("Senha encriptada: "+this.senha);
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDate getDataInstante() {
		return dataInstante;
	}
	
	
	
	
}
