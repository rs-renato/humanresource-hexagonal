package br.com.hrs.api.v1.docs;

import br.com.hrs.api.support.message.MensagemRetorno;
import br.com.hrs.api.v1.resource.EmployeeResource;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.json.JsonMergePatch;

@Api(value ="/v1/employees", tags={"Employee - Operations"})
@ApiImplicitParams(
		{@ApiImplicitParam(name="Authorization", dataType = "String", paramType = "header")})
public interface EmployeeOperationsDocumentable {

	/**
	 * Creates an Employee
	 * @param employeeResource representation of employee
	 * @return respose entity of {@link EmployeeResource} created
	 */
	@ApiOperation(value = "Creates an Employee", nickname="create", notes = "Creates an Employee returning itself saved", response = EmployeeResource.class)
	@ApiResponses(value = {
				@ApiResponse(code = 201, message = "Employee successfully created.", response = EmployeeResource.class),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class)})
	ResponseEntity<EmployeeResource> create(@ApiParam(value = "Employee Representation", required = true) EmployeeResource employeeResource);

	/**
	 * Updates an Employee
	 * @param id employee identifier
	 * @param employeeResource representation of employee to be updated
	 * @return respose entity of {@link EmployeeResource} updated
	 */
	@ApiOperation(value = "Updates an Employee", nickname="update", notes = "Updates an Employee returning itself updated", response = EmployeeResource.class)
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Employee successfully updated.", response = EmployeeResource.class),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class),
				@ApiResponse(code = 404, message = "Employee not found.", response = MensagemRetorno.class)})
	ResponseEntity<EmployeeResource> update(@ApiParam(value = "Employee Identifier", required = true) Integer id, @ApiParam(value = "Employee Representation", required = true) EmployeeResource employeeResource);

	/**
	 * Patches (update partially) an Employee
	 * @param id employee identifier
	 * @param jsonMergePatch resource of employee to be updated
	 * @return respose entity of {@link EmployeeResource} updated
	 */
	@ApiOperation(value = "Patches an Employee", nickname="patch", notes = "Patches (updates partially) an Employee returning itself updated", response = EmployeeResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Employee successfully updated.", response = EmployeeResource.class),
			@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class),
			@ApiResponse(code = 404, message = "Employee not found.", response = MensagemRetorno.class)})
	ResponseEntity<EmployeeResource> patch(@ApiParam(value="Employee Identifier", required=true) Integer id, @ApiParam(value="Employee Representation", required=true) JsonMergePatch jsonMergePatch);
	
	/**
	 * Deletes an Employee by its Id
	 * @param id employee identifier
	 * @return an {@link MensagemRetorno} cotaining the result of operation
	 */
	@ApiOperation(value = "Deletes an Employee by Id", nickname="deleteById", notes = "Deletes an Employee by Id returning an operation's confirmation")
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Employee successfully deleted."),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class),
				@ApiResponse(code = 404, message = "Employee not found.", response = MensagemRetorno.class)})
	ResponseEntity<MensagemRetorno> delete(@ApiParam(value = "Employee Identifier", required = true) Integer id);
}