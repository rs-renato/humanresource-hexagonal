package br.com.hrs.api.v1.controller;


import br.com.hrs.api.support.AssertionSupport;
import br.com.hrs.api.support.MensagemRetornoResponseEntitySupport;
import br.com.hrs.api.support.PatchSupport;
import br.com.hrs.api.v1.docs.DepartmentOperationsDocumentable;
import br.com.hrs.api.v1.mapper.DepartmentMapper;
import br.com.hrs.api.v1.resource.DepartmentResource;
import br.com.hrs.api.validation.FieldValidationStrategy;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.usecase.department.DepartmentUseCase;
import br.gov.go.sefaz.javaee.commons.resource.v1.MensagemRetorno;
import br.gov.go.sefaz.javaee.commons.resource.v1.MensagemRetornoCategoria;
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
@RequestMapping("/v1/departments")
public class DepartmentOperationsController implements DepartmentOperationsDocumentable {

	private static Logger logger = LogManager.getLogger(DepartmentOperationsController.class);

	private DepartmentUseCase departmentUseCase;
	private DepartmentMapper departmentMapper;
	private PatchSupport patchSupport;

	@Autowired
	public DepartmentOperationsController(DepartmentUseCase departmentUseCase, DepartmentMapper departmentMapper, PatchSupport patchSupport) {
		this.departmentUseCase = departmentUseCase;
		this.departmentMapper = departmentMapper;
		this.patchSupport = patchSupport;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DepartmentResource> create(@RequestBody @Validated(FieldValidationStrategy.Create.class) DepartmentResource departmentResource) {
		logger.info("Performing 'Create Department' Body:{}", departmentResource);
		Department departmentCreated = this.departmentUseCase.save(departmentMapper.toModel(departmentResource));
		logger.info("Department {} created on 'Create Department'", departmentCreated);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(departmentMapper.toResource(departmentCreated));
	}
	
	@PutMapping(value="/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DepartmentResource> update(@PathVariable Integer id, @Validated(FieldValidationStrategy.Update.class) @RequestBody DepartmentResource departmentResource) {
		logger.info("Performing 'Update Department' Id:{}, Body:{}", id, departmentResource);
		departmentResource.setId(id);
		this.departmentUseCase.update(departmentMapper.toModel(departmentResource));
		logger.info("Department {} updated on 'Update Department'", departmentResource);
		return ResponseEntity.ok(departmentResource);
	}

	@PatchMapping(value="/{id}", consumes = "application/merge-patch+json",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<DepartmentResource> patch(@PathVariable Integer id, @RequestBody JsonMergePatch jsonMergePatch) {
		logger.info("Performing 'Update (Patch) Department' Id:{}, MergePatch:{}", id, jsonMergePatch);
		Optional<Department> department = this.departmentUseCase.findById(id);
		AssertionSupport.assertResourceFound(department, "Department not found!");

		DepartmentResource departmentPatched = patchSupport.apply(jsonMergePatch, departmentMapper.toResource(departmentMapper.unwrap(department)));

		this.departmentUseCase.update(departmentMapper.toModel(departmentPatched));
		logger.info("Department {} updated on 'Update (Patch) Department'", departmentPatched);
		return ResponseEntity.ok(departmentPatched);
	}
	
	@DeleteMapping(value="/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MensagemRetorno> delete(@PathVariable Integer id) {
		logger.info("Performing 'Delete Department' Id:{}", id);
		this.departmentUseCase.deleteById(id);
		logger.info("Department {} deleted on 'Delete Department'", id);
		return MensagemRetornoResponseEntitySupport.createResponseEntity(
				MensagemRetornoCategoria.CONFIRMACAO, HttpStatus.OK, "Department successfully deleted");
	}
}