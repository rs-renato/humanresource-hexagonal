package br.com.hrs.core.repository;

import br.com.hrs.core.model.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public interface JobRepository {

    Logger logger = LogManager.getLogger(JobRepository.class);

    Job find(String jobId);

    String save(Job job);

    boolean update(Job job);

    Collection<Job> findAll();

    boolean delete(String jobId);

    boolean exists(String jobId);
}
