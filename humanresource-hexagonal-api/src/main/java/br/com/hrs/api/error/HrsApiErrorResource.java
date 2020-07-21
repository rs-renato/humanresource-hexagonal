package br.com.hrs.api.error;

import br.com.hrs.api.support.message.MensagemRetorno;
import br.com.hrs.api.support.message.MensagemRetornoCategoria;
import br.com.hrs.api.support.message.MensagemRetornoResponseEntitySupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/errors")
public class HrsApiErrorResource {
	
	@GetMapping("/404")
	public ResponseEntity<MensagemRetorno> notFound(){
		return MensagemRetornoResponseEntitySupport.createResponseEntity(MensagemRetornoCategoria.ERRO, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/405")
	public ResponseEntity<MensagemRetorno> notSupported(){
		return MensagemRetornoResponseEntitySupport.createResponseEntity(MensagemRetornoCategoria.ERRO,HttpStatus.METHOD_NOT_ALLOWED);
	}

	@GetMapping("/406")
	public ResponseEntity<MensagemRetorno> notAcceptable(){
		return MensagemRetornoResponseEntitySupport.createResponseEntity(MensagemRetornoCategoria.ERRO,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@GetMapping("/500")
	public ResponseEntity<MensagemRetorno> internalError() {
		return MensagemRetornoResponseEntitySupport.createResponseEntity(MensagemRetornoCategoria.ERRO,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}