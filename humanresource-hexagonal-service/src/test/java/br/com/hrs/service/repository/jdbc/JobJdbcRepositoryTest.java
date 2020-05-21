package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.service.repository.config.HrsJdbcConfiguration;
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

@DisplayName("Jdbc Repository - Job")
@ContextConfiguration(classes = HrsJdbcConfiguration.class)
@ExtendWith(SpringExtension.class)
public class JobJdbcRepositoryTest {

    public static final String JOB_ID = "HR_REP";
    public static final String NEW_JOB_ID = "NEW_JOB";

    Logger logger = LogManager.getLogger(JobJdbcRepositoryTest.class);

    @Inject
    private Repository<Job, String> jobJdbcRepository;

    @Test
    @DisplayName("Finds for null Job")
    public void test01() {
        Job job = jobJdbcRepository.find(null);
        Assertions.assertNull(job, "Job should be null");
    }

    @Test
    @DisplayName("Finds for inexistent Job")
    public void test02() {
        Job job = jobJdbcRepository.find("NO_EXISTENT_JOB");
        logger.info(job);
        Assertions.assertNull(job, "Job should be null");
    }

    @Test
    @DisplayName("Updates Job")
    public void test03() {
        Job job = jobJdbcRepository.find(JOB_ID);
        logger.info(job);
        job.setTitle(job.getTitle() + " altered");
        job.setMinSalary(job.getMinSalary() + 1);
        job.setMaxSalary(job.getMaxSalary() + 1);

        jobJdbcRepository.update(job);

        Job jobSaved  = jobJdbcRepository.find(JOB_ID);

        Assertions.assertEquals(jobSaved.getTitle(), job.getTitle(), "Job title should be altered");
        Assertions.assertEquals(jobSaved.getTitle(), job.getTitle(), "Job title should be altered");
        Assertions.assertEquals(jobSaved.getMinSalary(), job.getMinSalary(), "Job min salary should be altered");
        Assertions.assertEquals(jobSaved.getMaxSalary(), job.getMaxSalary(), "Job max salary should be altered");
    }

    @Test
    @DisplayName("Saves Job")
    public void test04() {
        Job job = new Job(NEW_JOB_ID, "new job", 1000f, 2000f);
        jobJdbcRepository.save(job);

        job = jobJdbcRepository.find(NEW_JOB_ID);

        Assertions.assertNotNull(job, "Job should be saved");
        Assertions.assertTrue(job.getTitle().contains("new"), "Job should be saved");
    }

    @Test
    @DisplayName("Finds all Jobs")
    public void test05() {
        Collection<Job> jobs = jobJdbcRepository.findAll();
        logger.info(jobs);
        Assertions.assertNotNull(jobs, "Jobs should be listed");
        Assertions.assertTrue(jobs.size() >= 19, "Jobs should be listed at all");

    }

    @Test
    @DisplayName("Finds for Job")
    public void test06() {
        Job job = jobJdbcRepository.find(JOB_ID);
        logger.info(job);
        Assertions.assertNotNull(job, "Job should not be null");
    }

    @Test
    @DisplayName("Deletes Job")
    public void test07() {
        Job job = jobJdbcRepository.find(NEW_JOB_ID);
        logger.info(job);
        jobJdbcRepository.delete(job.getId());
        boolean exists = jobJdbcRepository.exists(job.getId());
        Assertions.assertFalse(exists, "Job still existing");
    }

    @Test
    @DisplayName("Job exists")
    public void test08() {
        boolean exists = jobJdbcRepository.exists(JOB_ID);
        Assertions.assertTrue(exists, "Job should be existent");
    }

    @Test
    @DisplayName("Job doesn't exists")
    public void test09() {
        boolean exists = jobJdbcRepository.exists("NO_EXISTENT_JOB");
        Assertions.assertFalse(exists, "Job should not be existent");
    }

}