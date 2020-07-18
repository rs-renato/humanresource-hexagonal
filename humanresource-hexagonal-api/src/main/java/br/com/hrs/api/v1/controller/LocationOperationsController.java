package br.com.hrs.api.v1.controller;

import br.com.hrs.api.support.AssertionSupport;
import br.com.hrs.api.support.MensagemRetornoResponseEntitySupport;
import br.com.hrs.api.support.PatchSupport;
import br.com.hrs.api.v1.docs.LocationOperationsDocumentable;
import br.com.hrs.api.v1.mapper.LocationMapper;
import br.com.hrs.api.v1.resource.LocationResource;
import br.com.hrs.api.validation.FieldValidationStrategy;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.usecase.location.LocationUseCase;
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
@RequestMapping("/v1/locations")
public class LocationOperationsController implements LocationOperationsDocumentable {

	private static Logger logger = LogManager.getLogger(LocationOperationsController.class);

	private LocationUseCase locationUseCase;
	private LocationMapper locationMapper;
	private PatchSupport patchSupport;

	@Autowired
	public LocationOperationsController(LocationUseCase locationUseCase, LocationMapper locationMapper, PatchSupport patchSupport) {
		this.locationUseCase = locationUseCase;
		this.locationMapper = locationMapper;
		this.patchSupport = patchSupport;
	}

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
				 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<LocationResource> create(@Validated(FieldValidationStrategy.Create.class) @RequestBody LocationResource locationResource) {
		logger.info("Performing 'Create Location' Body:{}", locationResource);
		Location locationCreated = this.locationUseCase.save(locationMapper.toModel(locationResource));
		logger.info("Location {} created on 'Create Location'", locationCreated);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(locationMapper.toResource(locationCreated));
	}
	
	@PutMapping(value="/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<LocationResource> update(@PathVariable Integer id, @Validated(FieldValidationStrategy.Update.class)  @RequestBody LocationResource locationResource) {
		logger.info("Performing 'Update Location' Id:{}, Body:{}", id, locationResource);
		locationResource.setId(id);
		this.locationUseCase.update(locationMapper.toModel(locationResource));
		logger.info("Location {} updated on 'Update Location'", locationResource);
		return ResponseEntity.ok(locationResource);
	}

	@PatchMapping(value="/{id}", consumes = "application/merge-patch+json",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<LocationResource> patch(@PathVariable Integer id, @RequestBody JsonMergePatch jsonMergePatch) {
		logger.info("Performing 'Update (Patch) Location' Id:{}, MergePatch:{}", id, jsonMergePatch);
		Optional<Location> location = this.locationUseCase.findById(id);
		AssertionSupport.assertResourceFound(location, "Location not found!");

		LocationResource locationPatched = patchSupport.apply(jsonMergePatch, locationMapper.toResource(locationMapper.unwrap(location)));

		this.locationUseCase.update(locationMapper.toModel(locationPatched));
		logger.info("Location {} updated on 'Update (Patch) Location'", locationPatched);
		return ResponseEntity.ok(locationPatched);
	}

	@DeleteMapping(value="/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<MensagemRetorno> delete(@PathVariable Integer id) {
		logger.info("Performing 'Delete Location' Id:{}", id);
		this.locationUseCase.deleteById(id);
		logger.info("Location {} deleted on 'Delete Location'", id);
		return MensagemRetornoResponseEntitySupport.createResponseEntity(
				MensagemRetornoCategoria.CONFIRMACAO, HttpStatus.OK, "Location successfully deleted");
	}
}
