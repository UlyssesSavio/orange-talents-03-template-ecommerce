package com.br.mercadoLivre.validator;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.br.mercadoLivre.response.ErroDeFormularioResponse;


@RestControllerAdvice
public class ErroDeValidacaoHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public List<ErroDeFormularioResponse> handle(MethodArgumentNotValidException exception) {

		List<ErroDeFormularioResponse> dto = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeFormularioResponse erro = new ErroDeFormularioResponse(e.getField(), mensagem);
			dto.add(erro);
		});

		return dto;
	}
	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { IllegalStateException.class })
	public ErroDeFormularioResponse handleIlegal(IllegalStateException exception) {

		ErroDeFormularioResponse response = new ErroDeFormularioResponse(exception.getClass().getName(), exception.getLocalizedMessage());
		

		return response;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { IllegalArgumentException.class })
	public ErroDeFormularioResponse handleIlegal(IllegalArgumentException exception) {

		ErroDeFormularioResponse response = new ErroDeFormularioResponse(exception.getClass().getName(), exception.getLocalizedMessage());
		

		return response;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { MissingServletRequestPartException.class })
	public ErroDeFormularioResponse handleMissing(MissingServletRequestPartException exception) {

		ErroDeFormularioResponse response = new ErroDeFormularioResponse(exception.getClass().getName(), exception.getLocalizedMessage());
		

		return response;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { MaxUploadSizeExceededException.class})
	public ErroDeFormularioResponse handleFileGrande(MaxUploadSizeExceededException exception) {

		ErroDeFormularioResponse response = new ErroDeFormularioResponse(exception.getClass().getName(), exception.getLocalizedMessage());
		

		return response;
	}
	
	
}
