package br.com.hrs.core.repository.impl;

import br.com.hrs.core.model.Region;

import javax.inject.Named;

@Named
public class RegionMockRepository extends MockRepository<Region, Integer> {

    @Override
    public void loadMockDatabase() {

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
}
