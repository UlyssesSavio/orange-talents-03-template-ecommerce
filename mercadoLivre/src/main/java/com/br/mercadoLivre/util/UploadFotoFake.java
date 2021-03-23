package com.br.mercadoLivre.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class UploadFotoFake {
	
	private List<String> listaLinks = new ArrayList<String>();
	
	
	public UploadFotoFake() {
		
	}
	
	
	public UploadFotoFake(MultipartFile[] listaImagens, Long idUser) {
		for(int i=0; i<listaImagens.length; i++) {
			
			listaLinks.add("http://Imagens/+"+i+"/usuario/"+idUser);
		}
	}

	
	public List<String> getListaLinks() {
		
		
		
		return listaLinks;
	}

	
	
	
	

}
