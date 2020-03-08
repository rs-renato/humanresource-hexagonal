package br.com.hrs.service.repository;

import br.com.hrs.core.model.Job;
import br.com.hrs.core.port.outbound.JobRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.Collection;

@DisplayName("Database Service - Job")
@ContextConfiguration(classes = DatabaseConfiguration.class)
@ExtendWith(SpringExtension.class)
public class JobRepositoryJdbcTest {

    Logger logger = LogManager.getLogger(JobRepositoryJdbcTest.class);

    @Inject
    private JobRepository jobRepository;

    @Test
    @DisplayName("Search for null job")
    public void test01(){
        Job job = jobRepository.get(null);
        Assertions.assertNull(job, "Job should be null");
    }

    @Test
    @DisplayName("Search for inexistent job")
    public void test02(){
        Job job = jobRepository.get("NO_EXISTENT_JOB");
        logger.info(job);
        Assertions.assertNull(job, "Job should be null");
    }

    @Test
    @DisplayName("Updates Job")
    public void test03(){
        Job job = jobRepository.get("HR_REP");
        logger.info(job);
        job.setTitle(job.getTitle() + " altered");
        jobRepository.save(job);

        job = jobRepository.get("HR_REP");

        Assertions.assertTrue(job.getTitle().contains("altered"), "Job should be altered");
    }

    @Test
    @DisplayName("Saves Job")
    public void test04(){
        Job job = new Job("NEW_JOB", "new job", 1000f, 2000f);
        jobRepository.save(job);

        job = jobRepository.get("NEW_JOB");

        Assertions.assertTrue(job.getTitle().contains("new"), "Job should be saved");
    }

    @Test
    @DisplayName("List Jobs")
    public void test05(){
        Collection<Job> jobs = jobRepository.list();
        logger.info(jobs);
        Assertions.assertNotNull(jobs, "Jobs should be listed");
    }

    @Test
    @DisplayName("Deletes Jobs")
    public void test06(){
        Job job = jobRepository.get("NEW_JOB");
        logger.info(job);
        jobRepository.delete(job.getId());
        boolean exists = jobRepository.exists(job.getId());
        Assertions.assertFalse(exists, "Job should be deleted");
    }

    @Test
    @DisplayName("Job exists")
    public void test07(){
        boolean exists = jobRepository.exists("HR_REP");
        Assertions.assertTrue(exists, "Job should be existent");
    }

    @Test
    @DisplayName("Job doesn't exists")
    public void test08(){
        boolean exists = jobRepository.exists("NO_EXISTENT_JOB");
        Assertions.assertFalse(exists, "Job should not be existent");
    }

    @Test
    @DisplayName("Search for job")
    public void test(){
        Job job = jobRepository.get("HR_REP");
        logger.info(job);
        Assertions.assertNotNull(job, "Job should be null");
    }
}