package br.com.hrs.core.usecase.job;

import br.com.hrs.core.HrsBuildConfiguration;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.pagination.Pagination;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

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
        boolean exists = jobCrudUseCase.existsById(job.getId());
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
        Optional<Job> jobOpt = jobCrudUseCase.findById(jobSaved.getId());
        Assertions.assertEquals(jobSaved, jobOpt.get(), String.format("Job should be equals"));
    }

    @Test
    @DisplayName("Finds an Job")
    public void test03() {
        Optional<Job> jobOpt = jobCrudUseCase.findById(job.getId());
        Assertions.assertTrue(jobOpt.isPresent(), String.format("Job should not be null"));
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

        Optional<Job> jobOpt = jobCrudUseCase.findById(job.getId());

        Assertions.assertTrue(jobOpt.isPresent(), String.format("Job should not be null"));
        Assertions.assertEquals(jobOpt.get().getTitle(), job.getTitle(), String.format("Job tittle should be updated"));
    }

    @Test
    @DisplayName("Deletes an Job")
    public void test06() {

        jobCrudUseCase.deleteById(job.getId());

        Optional<Job> jobOpt = jobCrudUseCase.findById(job.getId());

        Assertions.assertFalse(jobOpt.isPresent(), String.format("Job should be null"));
    }

    @Test
    @DisplayName("Finds all Jobs Paginated")
    public void test07() {

        long count = jobCrudUseCase.count();

        int pageSize = 1;

        for (int i = 1; i < count; i++) {

            int page = i * pageSize;

            Pagination pagination = new  Pagination(page, pageSize);

            Collection<Job> jobs = jobCrudUseCase.findAll(pagination);
            Assertions.assertNotNull(jobs, "Jobs should be listed");
            Assertions.assertTrue(jobs.size() <= pageSize, "Jobs should be listed at all");

            new LinkedList<Job>(jobCrudUseCase.findAll())
                    .subList(page, page + pageSize)
                    .forEach(c -> c.equals(jobs.iterator().next()));
        }
    }
}
