package br.com.hrs.api.v1.docs;

import br.com.hrs.api.v1.resource.CountryResource;
import br.gov.go.sefaz.javaee.commons.resource.v1.MensagemRetorno;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(value ="/v1/countries", tags={"Country - Queries"})
@ApiImplicitParams(
		{@ApiImplicitParam(name="Authorization", dataType = "String", paramType = "header")})
public interface CountryQueriesDocumentable {

	/**
	 * List all Countries
	 * @return a collection of countries
	 */
	@ApiOperation(value = "List All Countries", nickname="listAll", notes = "List for all Countries returning a Country's list if there is any, otherwise 'MensagemRetorno'", response = CountryResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Countries found.", response = CountryResource[].class),
			@ApiResponse(code = 404, message = "Countries not found.", response = MensagemRetorno.class)})
	ResponseEntity<List<CountryResource>> listAll();
	
	/**
	 * Find an Country by Id
	 * @param id country identifier
	 * @return a country 
	 */
	@ApiOperation(value = "Finds a Country by its Id ", nickname="findById", notes = "Finds a Country by its Id returning it if there is any, otherwise 'MensagemRetorno'", response = CountryResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Country found.", response = CountryResource.class),
			@ApiResponse(code = 404, message = "Country not found.", response = MensagemRetorno.class)})
	ResponseEntity<CountryResource> find(@ApiParam(value="Country Identifier", required=true) @PathVariable String id);
}