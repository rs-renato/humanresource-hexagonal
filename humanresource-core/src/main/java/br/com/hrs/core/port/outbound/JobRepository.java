package br.com.hrs.core.port.outbound;

import br.com.hrs.core.model.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface JobRepository {

    Logger logger = LogManager.getLogger(JobRepository.class);

    Job get(Integer employeeId);

    @Transactional
    void save(Job employee);
    Collection<Job> list();

    @Transactional
    void delete(Integer employeeId);
}
