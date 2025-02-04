package com.br.mercadoLivre.requests;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;


//Nao consegui usar isso como parametro
public class ImagensRequest {
	
	@Size(min = 1)
	@NotNull
	private List<MultipartFile> imagens = new ArrayList<>();
	
	public ImagensRequest() {
		
	}

	public ImagensRequest(@Size(min = 1) List<MultipartFile> imagens) {
		this.imagens = imagens;
	}

	public List<MultipartFile> getImagens() {
		return imagens;
	}

	public void setImagens(List<MultipartFile> imagens) {
		this.imagens = imagens;
	}
	
	
	
	
	
	

}
