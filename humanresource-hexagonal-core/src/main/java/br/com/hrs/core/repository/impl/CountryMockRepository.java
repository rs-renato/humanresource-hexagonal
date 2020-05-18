package br.com.hrs.core.repository.impl;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Region;

import javax.inject.Named;

@Named
public class CountryMockRepository extends MockRepository<Country, String> {

    @Override
    public void loadMockDatabase() {

        Country country01 = new Country.Builder()
                .id("IT")
                .name("Italy")
                .region(new Region.Builder().id(1).build())
            .build();

        Country country02 = new Country.Builder()
                .id("JP")
                .name("Japan")
                .region(new Region.Builder().id(3).build())
            .build();


        this.database.put(country01.getId(), country01);
        this.database.put(country02.getId(), country02);
    }
}
