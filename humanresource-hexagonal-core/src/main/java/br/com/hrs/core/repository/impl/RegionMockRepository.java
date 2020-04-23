package br.com.hrs.core.repository.impl;

import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.Repository;

import javax.inject.Named;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Named
public class RegionMockRepository extends Repository<Region, Integer> {

    private Map<Integer, Region> database = new HashMap<>();

    public RegionMockRepository() {

        Region region01 = new Region.Builder()
                            .id(1)
                            .name("Europe")
                        .build();

        Region region02 = new Region.Builder()
                .id(2)
                .name("Americas")
                .build();

        this.database.put(region01.getId(), region01);
        this.database.put(region02.getId(), region02);
    }

    @Override
    public Region find(Integer regionId) {
        logger.info("Fake database -> find({}})", regionId);
        return this.database.get(regionId);
    }

    @Override
    public Integer save(Region region) {
        logger.info("Fake database ->  save({}})", region);
        this.database.put(region.getId(), region);
        return region.getId();
    }

    @Override
    public boolean update(Region region) {
        logger.info("Fake database ->  update({}})", region);
        return this.database.put(region.getId(), region) != null;
    }

    @Override
    public Collection<Region> findAll() {
        logger.info("Fake database ->  Collection<Region> findAll()");
        return this.database.values();
    }

    @Override
    public boolean delete(Integer regionId) {
        logger.info("Fake database ->  delete({}})", regionId);
        return this.database.remove(regionId) != null;
    }

    @Override
    public boolean exists(Integer regionId) {
        logger.info("Fake database ->  exists({}})", regionId);
        return this.database.containsKey(regionId);
    }
}
