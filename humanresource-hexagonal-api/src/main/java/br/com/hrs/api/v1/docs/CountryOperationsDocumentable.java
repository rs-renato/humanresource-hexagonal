package br.com.hrs.api.v1.docs;

import br.com.hrs.api.support.message.MensagemRetorno;
import br.com.hrs.api.v1.resource.CountryResource;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.json.JsonMergePatch;

@Api(value ="/v1/countries", tags={"Country - Operations"})
@ApiImplicitParams(
		{@ApiImplicitParam(name="Authorization", dataType = "String", paramType = "header")})
public interface CountryOperationsDocumentable {

	/**
	 * Creates an Country
	 * @param countryResource resource of country
	 * @return respose entity of {@link CountryResource} created
	 */
	@ApiOperation(value = "Creates an Country", nickname="create", notes = "Creates an Country returning itself saved", response = CountryResource.class)
	@ApiResponses(value = {
				@ApiResponse(code = 201, message = "Country successfully created.", response = CountryResource.class),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class)})
	ResponseEntity<CountryResource> create(@ApiParam(value="Country Representation", required=true) CountryResource countryResource);

	/**
	 * Updates an Country
	 * @param id country identifier
	 * @param countryResource resource of country to be updated
	 * @return respose entity of {@link CountryResource} updated
	 */
	@ApiOperation(value = "Updates an Country", nickname="update", notes = "Updates an Country returning itself updated", response = CountryResource.class)
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Country successfully updated.", response = CountryResource.class),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class),
				@ApiResponse(code = 404, message = "Country not found.", response = MensagemRetorno.class)})
	ResponseEntity<CountryResource> update(@ApiParam(value="Country Identifier", required=true) String id, @ApiParam(value="Country Representation", required=true) CountryResource countryResource);
	

	/**
	 * Patches (update partially) an Country
	 * @param id country identifier
	 * @param jsonMergePatch resource of country to be updated
	 * @return respose entity of {@link CountryResource} updated
	 */
	@ApiOperation(value = "Patches an Country", nickname="patch", notes = "Patches (updates partially) an Country returning itself updated", response = CountryResource.class)
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Country successfully updated.", response = CountryResource.class),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class),
				@ApiResponse(code = 404, message = "Country not found.", response = MensagemRetorno.class)})
	ResponseEntity<CountryResource> patch(@ApiParam(value="Country Identifier", required=true) String id, @ApiParam(value="Country Representation", required=true) JsonMergePatch jsonMergePatch);

	/**
	 * Deletes an Country by its Id
	 * @param id country identifier
	 * @return an {@link MensagemRetorno} cotaining the result of operation
	 */
	@ApiOperation(value = "Deletes an Country by Id", nickname="deleteById", notes = "Deletes an Country by Id returning an operation's confirmation")
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Country successfully deleted."),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class),
				@ApiResponse(code = 404, message = "Country not found.", response = MensagemRetorno.class)})
	ResponseEntity<MensagemRetorno> delete(@ApiParam(value="Country Identifier", required=true) String id);
}