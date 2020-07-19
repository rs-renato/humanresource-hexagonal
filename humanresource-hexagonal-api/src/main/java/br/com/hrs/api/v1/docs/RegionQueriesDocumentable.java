package br.com.hrs.api.v1.docs;

import br.com.hrs.api.v1.resource.RegionResource;
import br.gov.go.sefaz.javaee.commons.resource.v1.MensagemRetorno;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(value ="/v1/regions", tags={"Region - Queries"})
@ApiImplicitParams(
		{@ApiImplicitParam(name="Authorization", dataType = "String", paramType = "header")})
public interface RegionQueriesDocumentable {

	/**
	 * List all Regions
	 * @return a collection of regions
	 */
	@ApiOperation(value = "List All Regions", nickname="listAll", notes = "List for all Regions returning a Region's list if there is any, otherwise 'MensagemRetorno'", response = RegionResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Regions found.", response = RegionResource[].class),
			@ApiResponse(code = 404, message = "Regions not found.", response = MensagemRetorno.class)})
	ResponseEntity<List<RegionResource>> listAll();
	
	/**
	 * Find an Region by Id
	 * @param id region identifier
	 * @return a region 
	 */
	@ApiOperation(value = "Finds a Region by its Id ", nickname="findById", notes = "Finds a Region by its Id returning it if there is any, otherwise 'MensagemRetorno'", response = RegionResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Region found.", response = RegionResource.class),
			@ApiResponse(code = 404, message = "Region not found.", response = MensagemRetorno.class)})
	ResponseEntity<RegionResource> find(@ApiParam(value = "Region Identifier", required = true) @PathVariable Integer id);
}