package br.com.hrs.api.v1.docs;


import br.com.hrs.api.support.message.MensagemRetorno;
import br.com.hrs.api.v1.resource.DepartmentResource;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(value ="/v1/departments", tags={"Department - Queries"})
@ApiImplicitParams(
		{@ApiImplicitParam(name="Authorization", dataType = "String", paramType = "header")})
public interface DepartmentQueriesDocumentable {

	/**
	 * List all Departments
	 * @return a collection of departments
	 */
	@ApiOperation(value = "List All Departments", nickname="listAll", notes = "List for all Departments returning a Department's list if there is any, otherwise 'MensagemRetorno'", response = DepartmentResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Departments found.", response = DepartmentResource[].class),
			@ApiResponse(code = 404, message = "Departments not found.", response = MensagemRetorno.class)})
	ResponseEntity<List<DepartmentResource>> listAll(Pageable pageable);
	
	/**
	 * Find an Department by Id
	 * @param id department identifier
	 * @return a department 
	 */
	@ApiOperation(value = "Finds a Department by its Id ", nickname="findById", notes = "Finds a Department by its Id returning it if there is any, otherwise 'MensagemRetorno'", response = DepartmentResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Department found.", response = DepartmentResource.class),
			@ApiResponse(code = 404, message = "Department not found.", response = MensagemRetorno.class)})
	ResponseEntity<DepartmentResource> find(@ApiParam(value = "Department Identifier", required = true) @PathVariable Integer id);
}