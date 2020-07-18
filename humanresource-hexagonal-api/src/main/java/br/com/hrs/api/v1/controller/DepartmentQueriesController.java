package br.com.hrs.api.v1.controller;


import br.com.hrs.api.support.AssertionSupport;
import br.com.hrs.api.v1.docs.DepartmentQueriesDocumentable;
import br.com.hrs.api.v1.mapper.DepartmentMapper;
import br.com.hrs.api.v1.resource.DepartmentResource;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.usecase.department.DepartmentUseCase;
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
@RequestMapping("/v1/departments")
public class DepartmentQueriesController implements DepartmentQueriesDocumentable {

	private static Logger logger = LogManager.getLogger(DepartmentQueriesController.class);

	private DepartmentUseCase departmentUseCase;
	private DepartmentMapper departmentMapper;

	@Autowired
	public DepartmentQueriesController(DepartmentUseCase departmentUseCase, DepartmentMapper departmentMapper) {
		this.departmentUseCase = departmentUseCase;
		this.departmentMapper = departmentMapper;
	}

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<DepartmentResource>> listAll() {
		logger.info("Performing 'List All departments'..");
		List<Department> departments = this.departmentUseCase.findAll();
		AssertionSupport.assertResourceFound(departments, "departments not found!");
		List<DepartmentResource> departmentResources = departmentMapper.toResourceList(departments);
		logger.info("Found {} departments on 'List All departments'..", departmentResources.size());
		return ResponseEntity.ok(departmentResources);
	}

	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<DepartmentResource> find(@PathVariable Integer id) {
		logger.info("Performing 'Find Department' Id:{}", id);
		Optional<Department> department = this.departmentUseCase.findById(id);
		AssertionSupport.assertResourceFound(department, "Department not found!");
		DepartmentResource departmentResource = departmentMapper.toResource(departmentMapper.unwrap(department));
		logger.info("Department {} found on 'Find Department'", departmentResource);
		return ResponseEntity.ok(departmentResource);
	}
}