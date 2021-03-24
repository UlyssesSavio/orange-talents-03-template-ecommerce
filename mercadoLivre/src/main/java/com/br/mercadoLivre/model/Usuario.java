package com.br.mercadoLivre.model;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.mercadoLivre.util.SenhaLimpa;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Usuario implements UserDetails{

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Email
	@Column(unique = true, nullable=false)
	private String login;
	@NotBlank
	@Length(min=6)
	@Column( nullable=false)
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


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getPassword() {
		return senha;
	}


	@Override
	public String getUsername() {
		return login;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
	
}
