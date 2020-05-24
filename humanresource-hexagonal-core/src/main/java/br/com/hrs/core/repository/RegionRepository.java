package br.com.hrs.core.repository;

import br.com.hrs.core.model.Region;

import java.util.Optional;

public interface RegionRepository extends Repository<Region, Integer> {
     Optional<Region> findByName(String name);
}