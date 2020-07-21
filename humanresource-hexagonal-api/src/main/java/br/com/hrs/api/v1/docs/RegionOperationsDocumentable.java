package br.com.hrs.api.v1.docs;

import br.com.hrs.api.support.message.MensagemRetorno;
import br.com.hrs.api.v1.resource.RegionResource;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.json.JsonMergePatch;

@Api(value ="/v1/regions", tags={"Region - Operations"})
@ApiImplicitParams(
		{@ApiImplicitParam(name="Authorization", dataType = "String", paramType = "header")})
public interface RegionOperationsDocumentable {

	/**
	 * Creates an Region
	 * @param regionResource representation of region
	 * @return respose entity of {@link RegionResource} created
	 */
	@ApiOperation(value = "Creates an Region", nickname="create", notes = "Creates an Region returning itself saved", response = RegionResource.class)
	@ApiResponses(value = {
				@ApiResponse(code = 201, message = "Region successfully created.", response = RegionResource.class),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class)})
	ResponseEntity<RegionResource> create(@ApiParam(value = "Region Representation", required = true) RegionResource regionResource);

	/**
	 * Updates an Region
	 * @param id region identifier
	 * @param regionResource representation of region to be updated
	 * @return respose entity of {@link RegionResource} updated
	 */
	@ApiOperation(value = "Updates an Region", nickname="update", notes = "Updates an Region returning itself updated", response = RegionResource.class)
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Region successfully updated.", response = RegionResource.class),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class),
				@ApiResponse(code = 404, message = "Region not found.", response = MensagemRetorno.class)})
	ResponseEntity<RegionResource> update(@ApiParam(value = "Region Identifier", required = true) Integer id, @ApiParam(value = "Region Representation", required = true) RegionResource regionResource);

	/**
	 * Patches (update partially) an Region
	 * @param id region identifier
	 * @param jsonMergePatch resource of region to be updated
	 * @return respose entity of {@link RegionResource} updated
	 */
	@ApiOperation(value = "Patches an Region", nickname="patch", notes = "Patches (updates partially) an Region returning itself updated", response = RegionResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Region successfully updated.", response = RegionResource.class),
			@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class),
			@ApiResponse(code = 404, message = "Region not found.", response = MensagemRetorno.class)})
	ResponseEntity<RegionResource> patch(@ApiParam(value="Region Identifier", required=true) Integer id, @ApiParam(value="Region Representation", required=true) JsonMergePatch jsonMergePatch);

	/**
	 * Deletes an Region by its Id
	 * @param id region identifier
	 * @return an {@link MensagemRetorno} cotaining the result of operation
	 */
	@ApiOperation(value = "Deletes an Region by Id", nickname="deleteById", notes = "Deletes an Region by Id returning an operation's confirmation")
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Region successfully deleted."),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class),
				@ApiResponse(code = 404, message = "Region not found.", response = MensagemRetorno.class)})
	ResponseEntity<MensagemRetorno> delete(@ApiParam(value = "Region Identifier", required = true) Integer id);
}