package br.com.hrs.api.v1.controller;

import br.com.hrs.api.support.AssertionSupport;
import br.com.hrs.api.v1.docs.CountryQueriesDocumentable;
import br.com.hrs.api.v1.mapper.CountryMapper;
import br.com.hrs.api.v1.resource.CountryResource;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.usecase.country.CountryUseCase;
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
@RequestMapping("/v1/countries")
public class CountryQueriesController implements CountryQueriesDocumentable{
	
	private static Logger logger = LogManager.getLogger(CountryQueriesController.class);

	private CountryUseCase countryUseCase;
	private CountryMapper countryMapper;

	@Autowired
	public CountryQueriesController(CountryUseCase countryUseCase, CountryMapper countryMapper) {
		this.countryUseCase = countryUseCase;
		this.countryMapper = countryMapper;
	}

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<CountryResource>> listAll() {
		logger.info("Performing 'List All Countries'..");
		List<Country> countries = this.countryUseCase.findAll();
		AssertionSupport.assertResourceFound(countries, "Countries not found!");
		List<CountryResource> countryResources = countryMapper.toResourceList(countries);
		logger.info("Found {} countries on 'List All Countries'..", countryResources.size());
		return ResponseEntity.ok(countryResources);
	}

	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CountryResource> find(@PathVariable String id) {
		logger.info("Performing 'Find Country' Id:{}", id);
		Optional<Country> country = this.countryUseCase.findById(id);
		AssertionSupport.assertResourceFound(country, "Country not found!");
		CountryResource countryResource = countryMapper.toResource(countryMapper.unwrap(country));
		logger.info("Country {} found on 'Find Country'", countryResource);
		return ResponseEntity.ok(countryResource);
	}
}