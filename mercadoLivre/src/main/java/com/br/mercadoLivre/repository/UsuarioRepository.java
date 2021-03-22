package com.br.mercadoLivre.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.br.mercadoLivre.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Optional<Usuario> findByLogin(String username);

}
