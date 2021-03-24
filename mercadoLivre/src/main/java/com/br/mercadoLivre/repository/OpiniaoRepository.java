package com.br.mercadoLivre.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.br.mercadoLivre.model.Opiniao;

public interface OpiniaoRepository extends CrudRepository<Opiniao, Long>{

	Optional<Opiniao> findByProdutoId(Long id);

	Optional<List<Opiniao>> findAllByProdutoId(Long id);

}
