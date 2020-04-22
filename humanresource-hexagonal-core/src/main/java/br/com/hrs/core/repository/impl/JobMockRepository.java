package br.com.hrs.core.repository.impl;

import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.JobRepository;

import javax.inject.Named;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Named
public class JobMockRepository implements JobRepository {

    private Map<String, Job> database = new HashMap<>();

    public JobMockRepository() {

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

    @Override
    public Job find(String jobId) {
        logger.info("Fake database -> find({}})", jobId);
        return this.database.get(jobId);
    }

    @Override
    public String save(Job job) {
        logger.info("Fake database ->  save({}})", job);
        this.database.put(job.getId(), job);
        return job.getId();
    }

    @Override
    public void update(Job job) {
        logger.info("Fake database ->  update({}})", job);
        this.database.put(job.getId(), job);
    }

    @Override
    public Collection<Job> findAll() {
        logger.info("Fake database ->  Collection<Job> findAll()");
        return this.database.values();
    }

    @Override
    public void delete(String jobId) {
        logger.info("Fake database ->  delete({}})", jobId);
        this.database.remove(jobId);
    }

    @Override
    public boolean exists(String jobId) {
        logger.info("Fake database ->  exists({}})", jobId);
        return this.database.containsKey(jobId);
    }
}
