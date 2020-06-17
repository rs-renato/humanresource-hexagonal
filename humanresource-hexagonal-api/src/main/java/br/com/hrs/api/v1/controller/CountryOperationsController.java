package br.com.hrs.api.v1.controller;

import br.com.hrs.api.support.AssertionSupport;
import br.com.hrs.api.support.MensagemRetornoResponseEntitySupport;
import br.com.hrs.api.support.PatchSupport;
import br.com.hrs.api.v1.docs.CountryOperationsDocumentable;
import br.com.hrs.api.v1.mapper.CountryMapper;
import br.com.hrs.api.v1.resource.CountryResource;
import br.com.hrs.api.validation.FieldValidationStrategy;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.usecase.country.CountryUseCase;
import br.gov.go.sefaz.javaee.commons.resource.v1.MensagemRetorno;
import br.gov.go.sefaz.javaee.commons.resource.v1.MensagemRetornoCategoria;
import com.github.fge.jsonpatch.JsonPatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/countries")
public class CountryOperationsController implements CountryOperationsDocumentable {

	private static Logger logger = LogManager.getLogger(CountryOperationsController.class);

	private CountryUseCase countryUseCase;
	private CountryMapper countryMapper;
	private PatchSupport patchSupport;

	@Autowired
	public CountryOperationsController(CountryUseCase countryUseCase, CountryMapper countryMapper, PatchSupport patchSupport) {
		this.countryUseCase = countryUseCase;
		this.countryMapper = countryMapper;
		this.patchSupport = patchSupport;
	}

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
				 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CountryResource> create(@Validated(FieldValidationStrategy.Create.class) @RequestBody CountryResource countryResource) {
		logger.info("Performing 'Create Country' Body:{}", countryResource);
		Country countryCreated = this.countryUseCase.save(countryMapper.toModel(countryResource));
		logger.info("Country {} created on 'Create Country'", countryCreated);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(countryMapper.toResource(countryCreated));
	}
	
	@PutMapping(value="/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CountryResource> update(@PathVariable String id, @Validated(FieldValidationStrategy.Update.class)  @RequestBody CountryResource countryResource) {
		logger.info("Performing 'Update Country' Id:{}, Body:{}", id, countryResource);
		countryResource.setId(id);
		this.countryUseCase.update(countryMapper.toModel(countryResource));
		logger.info("Country {} updated on 'Update Country'", countryResource);
		return ResponseEntity.ok(countryResource);
	}
	
	@PatchMapping(value="/{id}/region", consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CountryResource> patch(@PathVariable String id, @Validated(FieldValidationStrategy.Patch.class) @RequestBody CountryResource countryResource) {
		logger.info("Performing 'Update (Patch) Country' Id:{}, Body:{}", id, countryResource);
		Optional<Country> country = this.countryUseCase.findById(id);
		AssertionSupport.assertResourceFound(country, "Country not found!");

		CountryResource countryResourceFound = countryMapper.toResource(countryMapper.unwrap(country));
		countryResource.setRegionId(countryResource.getRegionId());

		this.countryUseCase.update(countryMapper.toModel(countryResourceFound));
		logger.info("Country {} updated on 'Update (Patch) Country'", countryResourceFound);
		return ResponseEntity.ok(countryResourceFound);
	}

	@PatchMapping(value="/{id}", consumes = "application/json-patch+json",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CountryResource> patch(@PathVariable String id, @RequestBody  JsonPatch jsonPatch) {
		logger.info("Performing 'Update (Patch) Country' Id:{}, Patch:{}", id, jsonPatch);
		Optional<Country> country = this.countryUseCase.findById(id);
		AssertionSupport.assertResourceFound(country, "Country not found!");

		CountryResource countryPatched = patchSupport.apply(jsonPatch, countryMapper.toResource(countryMapper.unwrap(country)));

		this.countryUseCase.update(countryMapper.toModel(countryPatched));
		logger.info("Country {} updated on 'Update (Patch) Country'", countryPatched);
		return ResponseEntity.ok(countryPatched);
	}
	
	@DeleteMapping(value="/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<MensagemRetorno> delete(@PathVariable String id) {
		logger.info("Performing 'Delete Country' Id:{}", id);
		this.countryUseCase.deleteById(id);
		logger.info("Country {} deleted on 'Delete Country'", id);
		return MensagemRetornoResponseEntitySupport.createResponseEntity(
				MensagemRetornoCategoria.CONFIRMACAO, HttpStatus.OK, "Country successfully deleted");
	}
}
