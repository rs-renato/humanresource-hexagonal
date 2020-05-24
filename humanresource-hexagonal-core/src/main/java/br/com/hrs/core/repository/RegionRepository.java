package br.com.hrs.core.repository;

import br.com.hrs.core.model.Region;

public interface RegionRepository extends Repository<Region, Integer> {
     Region findByName(String regionName);
}