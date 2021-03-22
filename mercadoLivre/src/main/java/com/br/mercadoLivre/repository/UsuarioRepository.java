package com.br.mercadoLivre.repository;

import org.springframework.data.repository.CrudRepository;

import com.br.mercadoLivre.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
