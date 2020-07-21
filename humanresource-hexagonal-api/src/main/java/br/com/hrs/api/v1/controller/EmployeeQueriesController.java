package br.com.hrs.api.v1.controller;

import br.com.hrs.api.support.AssertionSupport;
import br.com.hrs.api.v1.docs.EmployeeQueriesDocumentable;
import br.com.hrs.api.v1.mapper.EmployeeMapper;
import br.com.hrs.api.v1.resource.EmployeeResource;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.usecase.employee.EmployeeUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeQueriesController implements EmployeeQueriesDocumentable{

	private static Logger logger = LogManager.getLogger(EmployeeQueriesController.class);

	private EmployeeUseCase employeeUseCase;
	private EmployeeMapper employeeMapper;

	@Autowired
	public EmployeeQueriesController(EmployeeUseCase employeeUseCase, EmployeeMapper employeeMapper) {
		this.employeeUseCase = employeeUseCase;
		this.employeeMapper = employeeMapper;
	}

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<EmployeeResource>> listAll() {
		logger.info("Performing 'List All Employees'..");
		List<Employee> employees = this.employeeUseCase.findAll();
		AssertionSupport.assertResourceFound(employees, "Employees not found!");
		List<EmployeeResource> employeeResources = employeeMapper.toResourceList(employees);
		logger.info("Found {} employees on 'List All Employees'..", employeeResources.size());
		return ResponseEntity.ok(employeeResources);
	}

	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<EmployeeResource> find(@PathVariable Integer id) {
		logger.info("Performing 'Find Employee' Id:{}", id);
		Optional<Employee> employee = this.employeeUseCase.findById(id);
		AssertionSupport.assertResourceFound(employee, "Employee not found!");
		EmployeeResource employeeResource = employeeMapper.toResource(employeeMapper.unwrap(employee));
		logger.info("Employee {} found on 'Find Employee'", employeeResource);
		return ResponseEntity.ok(employeeResource);
	}
}