package br.com.hrs.api.v1.controller;

import br.com.hrs.api.support.AssertionSupport;
import br.com.hrs.api.v1.docs.LocationQueriesDocumentable;
import br.com.hrs.api.v1.mapper.LocationMapper;
import br.com.hrs.api.v1.resource.LocationResource;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.pagination.Pagination;
import br.com.hrs.core.usecase.location.LocationUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/locations")
public class LocationQueriesController implements LocationQueriesDocumentable{
	
	private static Logger logger = LogManager.getLogger(LocationQueriesController.class);

	private LocationUseCase locationUseCase;
	private LocationMapper locationMapper;

	@Autowired
	public LocationQueriesController(LocationUseCase locationUseCase, LocationMapper locationMapper) {
		this.locationUseCase = locationUseCase;
		this.locationMapper = locationMapper;
	}

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<LocationResource>> listAll(Pageable pageable) {
		logger.info("Performing 'List All Locations'..");
		List<Location> locations = this.locationUseCase.findAll(new Pagination(pageable.getPageNumber(), pageable.getPageSize()));
		AssertionSupport.assertResourceFound(locations, "Locations not found!");
		List<LocationResource> locationResources = locationMapper.toResourceList(locations);
		logger.info("Found {} locations on 'List All Locations'..", locationResources.size());
		return ResponseEntity.ok(locationResources);
	}

	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<LocationResource> find(@PathVariable Integer id) {
		logger.info("Performing 'Find Location' Id:{}", id);
		Optional<Location> location = this.locationUseCase.findById(id);
		AssertionSupport.assertResourceFound(location, "Location not found!");
		LocationResource locationResource = locationMapper.toResource(locationMapper.unwrap(location));
		logger.info("Location {} found on 'Find Location'", locationResource);
		return ResponseEntity.ok(locationResource);
	}
}