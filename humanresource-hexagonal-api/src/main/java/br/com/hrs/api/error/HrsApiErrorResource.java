package br.com.hrs.api.error;

import br.com.hrs.api.support.MensagemRetornoResponseEntitySupport;
import br.gov.go.sefaz.javaee.commons.resource.v1.MensagemRetorno;
import br.gov.go.sefaz.javaee.commons.resource.v1.MensagemRetornoCategoria;
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
	
	@GetMapping("/500")
	public ResponseEntity<MensagemRetorno> internalError() {
		return MensagemRetornoResponseEntitySupport.createResponseEntity(MensagemRetornoCategoria.ERRO,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}