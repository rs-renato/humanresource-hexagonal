package br.com.hrs.core.usecase.job;

import br.com.hrs.core.HrsBuildConfiguration;
import br.com.hrs.core.model.Job;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.Collection;

@ContextConfiguration(classes = HrsBuildConfiguration.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Job Crud Use Case")
public class JobCrudUseCaseTest {

    @Inject
    private JobUseCase jobCrudUseCase;

    private static Job job;

    @BeforeAll
    public static void setUp() {
        job =  new Job.Builder()
                .id("AD_PRES")
                .title("President")
                .minSalary(20_080f)
                .maxSalay(30_000f)
                .build();
    }

    @Test
    @DisplayName("Exists an Jobs")
    public void test01() {
        boolean exists = jobCrudUseCase.exists(job.getId());
        Assertions.assertTrue(exists, String.format("Job should exist"));
    }

    @Test
    @DisplayName("Saves an Job")
    public void test02() {

        job =  new Job.Builder()
                .id("new JOB")
                .title("Vice President")
                .minSalary(15_080f)
                .maxSalay(25_000f)
                .build();

        Job jobSaved = jobCrudUseCase.save(job);
        Assertions.assertNotNull(jobSaved, String.format("Job saved should not be null"));
        Job jobFound = jobCrudUseCase.find(jobSaved.getId());
        Assertions.assertEquals(jobSaved, jobFound, String.format("Job should be equals"));
    }

    @Test
    @DisplayName("Finds an Job")
    public void test03() {
        Job jobFound = jobCrudUseCase.find(job.getId());
        Assertions.assertNotNull(jobFound, String.format("Job should not be null"));
    }

    @Test
    @DisplayName("Finds all Jobs")
    public void test04() {
        Collection<Job> jobs = jobCrudUseCase.findAll();
        Assertions.assertNotNull(jobs, String.format("Jobs should not be null"));
        Assertions.assertEquals(3, jobs.size(), String.format("Jobs size doesn't match"));
    }

    @Test
    @DisplayName("Updates an Job")
    public void test05() {

        job.setTitle(job.getTitle() + "updated");

        jobCrudUseCase.update(job);

        Job jobUpdated = jobCrudUseCase.find(job.getId());

        Assertions.assertNotNull(jobUpdated, String.format("Job should not be null"));
        Assertions.assertEquals(jobUpdated.getTitle(), job.getTitle(), String.format("Job tittle should be updated"));
    }

    @Test
    @DisplayName("Deletes an Job")
    public void test06() {

        jobCrudUseCase.delete(job.getId());

        Job jobUpdated = jobCrudUseCase.find(job.getId());

        Assertions.assertNull(jobUpdated, String.format("Job should be null"));
    }
}
