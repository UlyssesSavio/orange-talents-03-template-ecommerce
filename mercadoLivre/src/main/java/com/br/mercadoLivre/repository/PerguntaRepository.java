package com.br.mercadoLivre.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.br.mercadoLivre.model.Pergunta;

public interface PerguntaRepository extends CrudRepository<Pergunta, Long>{

	Optional<Pergunta> findByProdutoId(Long id);

	Optional<List<Pergunta>> findAllByProdutoId(Long id);
	
	

}
