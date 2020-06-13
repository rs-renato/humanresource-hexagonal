package br.com.hrs.api.v1.country.controller;

import br.com.hrs.api.mapper.CountryMapper;
import br.com.hrs.api.v1.country.docs.CountryQueriesDocumentable;
import br.com.hrs.api.v1.country.resource.CountryResource;
import br.com.hrs.core.usecase.country.CountryUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

	@GetMapping
	public ResponseEntity<List<CountryResource>> listAll() {
		logger.info("Performing 'List All Countries'..");
		List<CountryResource> countriesFound = countryMapper.toResourceList(this.countryUseCase.findAll());
		logger.info("Found {} countries on 'List All Countries'..", countriesFound.size());
		return ResponseEntity.ok(countriesFound);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CountryResource> find(@PathVariable String id) {
		logger.info("Performing 'Find Country' Id:{}", id);
		CountryResource countryFound = countryMapper.toResource(countryMapper.unwrap(this.countryUseCase.findById(id)));
		logger.info("Country {} found on 'Find Country'", countryFound);
		return ResponseEntity.ok(countryFound);
	}
}