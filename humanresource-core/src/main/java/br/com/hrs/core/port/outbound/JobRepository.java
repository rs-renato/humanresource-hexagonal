package br.com.hrs.core.port.outbound;

import br.com.hrs.core.model.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public interface JobRepository {

    Logger logger = LogManager.getLogger(JobRepository.class);

    Job get(String jobId);

    void save(Job job);

    Collection<Job> list();

    void delete(String jobId);

    boolean exists(String jobId);
}
