package br.com.hrs.api.v1.controller;

import br.com.hrs.api.support.AssertionSupport;
import br.com.hrs.api.support.PatchSupport;
import br.com.hrs.api.support.message.MensagemRetorno;
import br.com.hrs.api.support.message.MensagemRetornoCategoria;
import br.com.hrs.api.support.message.MensagemRetornoResponseEntitySupport;
import br.com.hrs.api.v1.docs.CountryOperationsDocumentable;
import br.com.hrs.api.v1.mapper.CountryMapper;
import br.com.hrs.api.v1.resource.CountryResource;
import br.com.hrs.api.validation.FieldValidationStrategy;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.usecase.country.CountryUseCase;
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

	@PatchMapping(value="/{id}", consumes = "application/merge-patch+json",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CountryResource> patch(@PathVariable String id, @RequestBody JsonMergePatch jsonMergePatch) {
		logger.info("Performing 'Update (Patch) Country' Id:{}, MergePatch:{}", id, jsonMergePatch);
		Optional<Country> country = this.countryUseCase.findById(id);
		AssertionSupport.assertResourceFound(country, "Country not found!");

		CountryResource countryPatched = patchSupport.apply(jsonMergePatch, countryMapper.toResource(countryMapper.unwrap(country)));

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
