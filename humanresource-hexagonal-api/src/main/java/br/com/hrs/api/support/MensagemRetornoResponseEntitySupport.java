package br.com.hrs.api.support;

import br.gov.go.sefaz.javaee.commons.resource.v1.MensagemRetorno;
import br.gov.go.sefaz.javaee.commons.resource.v1.MensagemRetornoBuilder;
import br.gov.go.sefaz.javaee.commons.resource.v1.MensagemRetornoCategoria;
import br.gov.go.sefaz.javaee.service.rest.support.ResponseEntitySupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class MensagemRetornoResponseEntitySupport {
	
	private static Logger logger = LogManager.getLogger(MensagemRetornoResponseEntitySupport.class);

	private MensagemRetornoResponseEntitySupport() {
	}

	public static ResponseEntity<MensagemRetorno> createResponseEntity(MensagemRetornoCategoria categoria, HttpStatus httpStatus, List<String> detalhes){
		return createResponseEntity(categoria, httpStatus.getReasonPhrase(), httpStatus, detalhes.toArray(new String[0]));
	}
	
	public static ResponseEntity<MensagemRetorno> createResponseEntity(MensagemRetornoCategoria categoria, HttpStatus httpStatus, String ...detalhes){
		return createResponseEntity(categoria, httpStatus.getReasonPhrase(), httpStatus, detalhes);
	}
	
	public static ResponseEntity<MensagemRetorno> createResponseEntity(MensagemRetornoCategoria categoria,  String destricao, HttpStatus httpStatus, String ...detalhes){
		logger.debug("Building MesangemRetorno: categoria={}, destricao={}, httpStatus={}, detalhes={}", categoria, destricao, httpStatus, detalhes);
		return ResponseEntitySupport.buildResponse(
				MensagemRetornoBuilder.builder()
					.categoria(categoria)
					.codigo(httpStatus.value())
					.descricao(destricao)
					.detalhes(detalhes)
					.buildMensagem(),
					httpStatus
				); 
	}
}

