package br.com.hrs.api.v1.docs;

import br.com.hrs.api.v1.resource.LocationResource;
import br.gov.go.sefaz.javaee.commons.resource.v1.MensagemRetorno;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

@Api(value ="/v1/locations", tags={"Location - Operations"})
@ApiImplicitParams(
		{@ApiImplicitParam(name="Authorization", dataType = "String", paramType = "header")})
public interface LocationOperationsDocumentable {

	/**
	 * Creates an Location
	 * @param locationResource representation of location
	 * @return respose entity of {@link LocationResource} created
	 */
	@ApiOperation(value = "Creates an Location", nickname="create", notes = "Creates an Location returning itself saved", response = LocationResource.class)
	@ApiResponses(value = {
				@ApiResponse(code = 201, message = "Location successfully created.", response = LocationResource.class),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class)})
	public ResponseEntity<LocationResource> create(@ApiParam(value = "Location Representation", required = true) LocationResource locationResource);

	/**
	 * Updates an Location
	 * @param id location identifier
	 * @param locationResource representation of location to be updated
	 * @return respose entity of {@link LocationResource} updated
	 */
	@ApiOperation(value = "Updates an Location", nickname="update", notes = "Updates an Location returning itself updated", response = LocationResource.class)
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Location successfully updated.", response = LocationResource.class),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class),
				@ApiResponse(code = 404, message = "Location not found.", response = MensagemRetorno.class)})
	public ResponseEntity<LocationResource> update(@ApiParam(value = "Location Identifier", required = true) Integer id, @ApiParam(value = "Location Representation", required = true) LocationResource locationResource);
	
	/**
	 * Deletes an Location by its Id
	 * @param id location identifier
	 * @return an {@link MensagemRetorno} cotaining the result of operation
	 */
	@ApiOperation(value = "Deletes an Location by Id", nickname="deleteById", notes = "Deletes an Location by Id returning an operation's confirmation")
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Location successfully deleted."),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class),
				@ApiResponse(code = 404, message = "Location not found.", response = MensagemRetorno.class)})
	public ResponseEntity<MensagemRetorno> delete(@ApiParam(value = "Location Identifier", required = true) Integer id);
}