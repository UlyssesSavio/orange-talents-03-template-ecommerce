package com.br.mercadoLivre.response;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.br.mercadoLivre.model.Opiniao;
import com.br.mercadoLivre.model.Pergunta;
import com.br.mercadoLivre.model.Produto;
import com.br.mercadoLivre.repository.OpiniaoRepository;
import com.br.mercadoLivre.repository.PerguntaRepository;
import com.br.mercadoLivre.repository.ProdutoRepository;

import io.jsonwebtoken.lang.Assert;

public class DetalhePaginaResponse {

	private ProdutoResponse produtoResponse;
	private List<PerguntaResponse> perguntaResponse;
	private List<OpiniaoResponse> opiniaoResponse;

	private double mediaOpiniao = 0;
	private int totalDeNotas = 0;

	public DetalhePaginaResponse(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository,
			OpiniaoRepository opiniaoRepository, Long id) {

		Optional<Produto> produtoOptional = produtoRepository.findById(id);
		Optional<List<Pergunta>> perguntaOptional = perguntaRepository.findAllByProdutoId(id);
		Optional<List<Opiniao>> opiniaoOptional = opiniaoRepository.findAllByProdutoId(id);
		
		Assert.state(produtoOptional.isPresent(), "Id de produto invalido");

		if (!perguntaOptional.isPresent()) {
			this.perguntaResponse = null;
		} else {
			this.perguntaResponse = converterPergunta(perguntaOptional.get());

		}

		if (!opiniaoOptional.isPresent()) {
			this.opiniaoResponse = null;
		} else {
			this.opiniaoResponse = converterOpiniao(opiniaoOptional.get());

		}

		this.produtoResponse = new ProdutoResponse(produtoOptional.get());
		
		
		if(totalDeNotas>1) mediaOpiniao = mediaOpiniao/totalDeNotas;

	}

	private List<OpiniaoResponse> converterOpiniao(List<Opiniao> opinioes) {

		List<OpiniaoResponse> convertido = opinioes.stream().map(temp -> {
			OpiniaoResponse perg = new OpiniaoResponse(temp);
			totalDeNotas++;
			mediaOpiniao=mediaOpiniao+perg.getNota();
			return perg;
		}).collect(Collectors.toList());

		return convertido;
	}

	private List<PerguntaResponse> converterPergunta(List<Pergunta> perguntas) {

		List<PerguntaResponse> convertido = perguntas.stream().map(temp -> {
			PerguntaResponse perg = new PerguntaResponse(temp);
			return perg;
		}).collect(Collectors.toList());

		return convertido;
	}

	public ProdutoResponse getProdutoResponse() {
		return produtoResponse;
	}

	public List<PerguntaResponse> getPerguntaResponse() {
		return perguntaResponse;
	}

	public List<OpiniaoResponse> getOpiniaoResponse() {
		return opiniaoResponse;
	}

	public double getMediaOpiniao() {
		return mediaOpiniao;
	}

	public int getTotalDeNotas() {
		return totalDeNotas;
	}

}
