package com.br.mercadoLivre.repository;

import org.springframework.data.repository.CrudRepository;

import com.br.mercadoLivre.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long>{

}
