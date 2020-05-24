package br.com.hrs.service.repository.jpa;

import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.service.repository.config.HrsJpaConfiguration;
import br.com.hrs.service.repository.jdbc.JobJdbcRepositoryTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.Collection;

@Disabled
@DisplayName("Jpa Repository - Job")
@ContextConfiguration(classes = HrsJpaConfiguration.class)
@ExtendWith(SpringExtension.class)
public class JobJpaRepositoryTest {

    private static final String JOB_ID = "HR_REP";
    private static final String NEW_JOB_ID = "NEW_JOB";

    Logger logger = LogManager.getLogger(JobJdbcRepositoryTest.class);

    @Inject
    private Repository<Job, String> jobJpaRepository;

    @Test
    @DisplayName("Finds for null Job")
    public void test01() {
        Job job = jobJpaRepository.find(null);
        Assertions.assertNull(job, "Job should be null");
    }

    @Test
    @DisplayName("Finds for inexistent Job")
    public void test02() {
        Job job = jobJpaRepository.find("NO_EXISTENT_JOB");
        logger.info(job);
        Assertions.assertNull(job, "Job should be null");
    }

    @Test
    @DisplayName("Updates Job")
    public void test03() {
        Job job = jobJpaRepository.find(JOB_ID);
        logger.info(job);
        job.setTitle(job.getTitle() + " altered");
        job.setMinSalary(job.getMinSalary() + 1);
        job.setMaxSalary(job.getMaxSalary() + 1);

        jobJpaRepository.update(job);

        Job jobSaved  = jobJpaRepository.find(JOB_ID);

        Assertions.assertEquals(jobSaved.getTitle(), job.getTitle(), "Job title should be altered");
        Assertions.assertEquals(jobSaved.getTitle(), job.getTitle(), "Job title should be altered");
        Assertions.assertEquals(jobSaved.getMinSalary(), job.getMinSalary(), "Job min salary should be altered");
        Assertions.assertEquals(jobSaved.getMaxSalary(), job.getMaxSalary(), "Job max salary should be altered");
    }

    @Test
    @DisplayName("Saves Job")
    public void test04() {
        Job job = new Job(NEW_JOB_ID, "new job", 1000f, 2000f);
        jobJpaRepository.save(job);

        job = jobJpaRepository.find(NEW_JOB_ID);

        Assertions.assertNotNull(job, "Job should be saved");
        Assertions.assertTrue(job.getTitle().contains("new"), "Job should be saved");
    }

    @Test
    @DisplayName("Finds all Jobs")
    public void test05() {
        Collection<Job> jobs = jobJpaRepository.findAll();
        logger.info(jobs);
        Assertions.assertNotNull(jobs, "Jobs should be listed");
        Assertions.assertTrue(jobs.size() >= 19, "Jobs should be listed at all");

    }

    @Test
    @DisplayName("Finds for Job")
    public void test06() {
        Job job = jobJpaRepository.find(JOB_ID);
        logger.info(job);
        Assertions.assertNotNull(job, "Job should not be null");
    }

    @Test
    @DisplayName("Deletes Job")
    public void test07() {
        Job job = jobJpaRepository.find(NEW_JOB_ID);
        logger.info(job);
        jobJpaRepository.delete(job.getId());
        boolean exists = jobJpaRepository.exists(job.getId());
        Assertions.assertFalse(exists, "Job still existing");
    }

    @Test
    @DisplayName("Job exists")
    public void test08() {
        boolean exists = jobJpaRepository.exists(JOB_ID);
        Assertions.assertTrue(exists, "Job should be existent");
    }

    @Test
    @DisplayName("Job doesn't exists")
    public void test09() {
        boolean exists = jobJpaRepository.exists("NO_EXISTENT_JOB");
        Assertions.assertFalse(exists, "Job should not be existent");
    }
}