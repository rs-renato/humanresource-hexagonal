package br.com.hrs.core.repository.impl;

import br.com.hrs.core.model.Job;

import javax.inject.Named;

@Named
public class JobMockRepository extends MockRepository<Job, String> {

    @Override
    public void loadMockDatabase() {

        Job job01 = new Job.Builder()
                        .id("AD_PRES")
                        .title("President")
                        .minSalary(20080f)
                        .maxSalay(40000f)
                     .build();

        Job job02 = new Job.Builder()
                .id("AD_VP")
                .title("Administration Vice President")
                .minSalary(15000f)
                .maxSalay(30000f)
                .build();


        this.database.put(job01.getId(), job01);
        this.database.put(job02.getId(), job02);
    }
}
