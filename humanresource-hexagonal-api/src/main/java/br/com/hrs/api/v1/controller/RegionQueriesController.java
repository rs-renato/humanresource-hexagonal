package br.com.hrs.api.v1.controller;

import br.com.hrs.api.support.AssertionSupport;
import br.com.hrs.api.v1.docs.RegionQueriesDocumentable;
import br.com.hrs.api.v1.mapper.RegionMapper;
import br.com.hrs.api.v1.resource.RegionResource;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.pagination.Pagination;
import br.com.hrs.core.usecase.region.RegionUseCase;
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
@RequestMapping("/v1/regions")
public class RegionQueriesController implements RegionQueriesDocumentable{
	
	private static Logger logger = LogManager.getLogger(RegionQueriesController.class);

	private RegionUseCase regionUseCase;
	private RegionMapper regionMapper;

	@Autowired
	public RegionQueriesController(RegionUseCase regionUseCase, RegionMapper regionMapper) {
		this.regionUseCase = regionUseCase;
		this.regionMapper = regionMapper;
	}

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<RegionResource>> listAll(Pageable pageable) {
		logger.info("Performing 'List All Region'..");
		List<Region> region = this.regionUseCase.findAll(new Pagination(pageable.getPageNumber(), pageable.getPageSize()));
		AssertionSupport.assertResourceFound(region, "Region not found!");
		List<RegionResource> regionResources = regionMapper.toResourceList(region);
		logger.info("Found {} region on 'List All Region'..", regionResources.size());
		return ResponseEntity.ok(regionResources);
	}

	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<RegionResource> find(@PathVariable Integer id) {
		logger.info("Performing 'Find Region' Id:{}", id);
		Optional<Region> region = this.regionUseCase.findById(id);
		AssertionSupport.assertResourceFound(region, "Region not found!");
		RegionResource regionResource = regionMapper.toResource(regionMapper.unwrap(region));
		logger.info("Region {} found on 'Find Region'", regionResource);
		return ResponseEntity.ok(regionResource);
	}
}