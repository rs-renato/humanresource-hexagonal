package br.com.hrs.api.v1.docs;

import br.com.hrs.api.v1.resource.LocationResource;
import br.gov.go.sefaz.javaee.commons.resource.v1.MensagemRetorno;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(value ="/v1/locations", tags={"Location - Queries"})
@ApiImplicitParams(
		{@ApiImplicitParam(name="Authorization", dataType = "String", paramType = "header")})
public interface LocationQueriesDocumentable {

	/**
	 * List all Locations
	 * @return a collection of locations
	 */
	@ApiOperation(value = "List All Locations", nickname="listAll", notes = "List for all Locations returning a Location's list if there is any, otherwise 'MensagemRetorno'", response = LocationResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Locations found.", response = LocationResource[].class),
			@ApiResponse(code = 404, message = "Locations not found.", response = MensagemRetorno.class)})
	ResponseEntity<List<LocationResource>> listAll();
	
	/**
	 * Find an Location by Id
	 * @param id location identifier
	 * @return a location 
	 */
	@ApiOperation(value = "Finds a Location by its Id ", nickname="findById", notes = "Finds a Location by its Id returning it if there is any, otherwise 'MensagemRetorno'", response = LocationResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Location found.", response = LocationResource.class),
			@ApiResponse(code = 404, message = "Location not found.", response = MensagemRetorno.class)})
	ResponseEntity<LocationResource> find(@ApiParam(value = "Location Identifier", required = true) @PathVariable Integer id);
}