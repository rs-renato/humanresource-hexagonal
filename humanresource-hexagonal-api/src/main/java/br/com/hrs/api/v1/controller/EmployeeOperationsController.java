package br.com.hrs.api.v1.controller;

import br.com.hrs.api.support.AssertionSupport;
import br.com.hrs.api.support.PatchSupport;
import br.com.hrs.api.support.message.MensagemRetorno;
import br.com.hrs.api.support.message.MensagemRetornoCategoria;
import br.com.hrs.api.support.message.MensagemRetornoResponseEntitySupport;
import br.com.hrs.api.v1.docs.EmployeeOperationsDocumentable;
import br.com.hrs.api.v1.mapper.EmployeeMapper;
import br.com.hrs.api.v1.resource.EmployeeResource;
import br.com.hrs.api.validation.FieldValidationStrategy;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.usecase.employee.EmployeeUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonMergePatch;
import java.util.Optional;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeOperationsController implements EmployeeOperationsDocumentable {

	private static Logger logger = LogManager.getLogger(EmployeeOperationsController.class);

	private EmployeeUseCase employeeUseCase;
	private EmployeeMapper employeeMapper;
	private PatchSupport patchSupport;

	@Autowired
	public EmployeeOperationsController(EmployeeUseCase employeeUseCase, EmployeeMapper employeeMapper, PatchSupport patchSupport) {
		this.employeeUseCase = employeeUseCase;
		this.employeeMapper = employeeMapper;
		this.patchSupport = patchSupport;
	}

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
				 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<EmployeeResource> create(@Validated(FieldValidationStrategy.Create.class) @RequestBody EmployeeResource employeeResource) {
		logger.info("Performing 'Create Employee' Body:{}", employeeResource);
		Employee employeeCreated = this.employeeUseCase.save(employeeMapper.toModel(employeeResource));
		logger.info("Employee {} created on 'Create Employee'", employeeCreated);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(employeeMapper.toResource(employeeCreated));
	}
	
	@PutMapping(value="/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<EmployeeResource> update(@PathVariable Integer id, @Validated(FieldValidationStrategy.Update.class)  @RequestBody EmployeeResource employeeResource) {
		logger.info("Performing 'Update Employee' Id:{}, Body:{}", id, employeeResource);
		employeeResource.setId(id);
		this.employeeUseCase.update(employeeMapper.toModel(employeeResource));
		logger.info("Employee {} updated on 'Update Employee'", employeeResource);
		return ResponseEntity.ok(employeeResource);
	}

	@PatchMapping(value="/{id}", consumes = "application/merge-patch+json",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<EmployeeResource> patch(@PathVariable Integer id, @RequestBody JsonMergePatch jsonMergePatch) {
		logger.info("Performing 'Update (Patch) Employee' Id:{}, MergePatch:{}", id, jsonMergePatch);
		Optional<Employee> employee = this.employeeUseCase.findById(id);
		AssertionSupport.assertResourceFound(employee, "Employee not found!");

		EmployeeResource employeePatched = patchSupport.apply(jsonMergePatch, employeeMapper.toResource(employeeMapper.unwrap(employee)));

		this.employeeUseCase.update(employeeMapper.toModel(employeePatched));
		logger.info("Employee {} updated on 'Update (Patch) Employee'", employeePatched);
		return ResponseEntity.ok(employeePatched);
	}

	@DeleteMapping(value="/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<MensagemRetorno> delete(@PathVariable Integer id) {
		logger.info("Performing 'Delete Employee' Id:{}", id);
		this.employeeUseCase.deleteById(id);
		logger.info("Employee {} deleted on 'Delete Employee'", id);
		return MensagemRetornoResponseEntitySupport.createResponseEntity(
				MensagemRetornoCategoria.CONFIRMACAO, HttpStatus.OK, "Employee successfully deleted");
	}
}
