package br.com.hrs.core.port.outbound.mock;

import br.com.hrs.core.model.Job;
import br.com.hrs.core.port.outbound.JobRepository;

import javax.inject.Named;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Named
public class JobMockRepository implements JobRepository {

    private Map<String, Job> database = new HashMap<>();

    public JobMockRepository() {
        this.database.put("AN", new Job("AN","Analyst", 1_000f, 3_000f));
        this.database.put("AR", new Job("AR","Architect", 3_000f, 5_000f));
    }

    @Override
    public Job get(Integer jobId) {
        logger.info("Fake database ->  Job get({}})", jobId);
        return this.database.get(jobId);
    }

    @Override
    public void save(Job job) {
        logger.info("Fake database ->  save({}})", job);
        this.database.put(job.getId(), job);
    }

    @Override
    public Collection<Job> list() {
        logger.info("Fake database ->  Collection<Job> list()");
        return this.database.values();
    }

    @Override
    public void delete(Integer jobId) {
        logger.info("Fake database ->  delete({}})", jobId);
        this.database.remove(jobId);
    }
}
