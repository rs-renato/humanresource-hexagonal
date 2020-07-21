package br.com.hrs.api.v1.docs;

import br.com.hrs.api.support.message.MensagemRetorno;
import br.com.hrs.api.v1.resource.EmployeeResource;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(value ="/v1/employees", tags={"Employee - Queries"})
@ApiImplicitParams(
		{@ApiImplicitParam(name="Authorization", dataType = "String", paramType = "header")})
public interface EmployeeQueriesDocumentable {

	/**
	 * List all Employees
	 * @return a collection of employees
	 */
	@ApiOperation(value = "List All Employees", nickname="listAll", notes = "List for all Employees returning a Employee's list if there is any, otherwise 'MensagemRetorno'", response = EmployeeResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Employees found.", response = EmployeeResource[].class),
			@ApiResponse(code = 404, message = "Employees not found.", response = MensagemRetorno.class)})
	ResponseEntity<List<EmployeeResource>> listAll();
	
	/**
	 * Find an Employee by Id
	 * @param id employee identifier
	 * @return a employee 
	 */
	@ApiOperation(value = "Finds a Employee by its Id ", nickname="findById", notes = "Finds a Employee by its Id returning it if there is any, otherwise 'MensagemRetorno'", response = EmployeeResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Employee found.", response = EmployeeResource.class),
			@ApiResponse(code = 404, message = "Employee not found.", response = MensagemRetorno.class)})
	ResponseEntity<EmployeeResource> find(@ApiParam(value = "Employee Identifier", required = true) @PathVariable Integer id);
}