package br.com.hrs.persistence.repository;

import br.com.hrs.core.exception.HrsMandatoryException;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.JobRepository;
import br.com.hrs.core.repository.pagination.Pagination;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public abstract class JobRepositoryTest {

    private static final String JOB_ID = "HR_REP";
    private static final String NEW_JOB_ID = "NEW_JOB";

    protected abstract JobRepository getJobRepository();
    
    @Test
    @DisplayName("Finds for null Job")
    public void test01() {

        RuntimeException exception = Assertions.assertThrows(HrsMandatoryException.class, () -> {
            getJobRepository().findById(null);
        });

        Assertions.assertTrue(exception.getMessage().matches("Job.*mandatory"), String.format("Job ID mandatory message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Finds for inexistent Job")
    public void test02() {
        Optional<Job> jobOpt = getJobRepository().findById("NO_EXISTENT_JOB");
        Assertions.assertFalse(jobOpt.isPresent(), "Job should be null");
    }

    @Test
    @DisplayName("Updates Job")
    public void test03() {
        Optional<Job> jobOpt = getJobRepository().findById(JOB_ID);
        jobOpt.get().setTitle(jobOpt.get().getTitle() + " altered");
        jobOpt.get().setMinSalary(jobOpt.get().getMinSalary() + 1);
        jobOpt.get().setMaxSalary(jobOpt.get().getMaxSalary() + 1);

        getJobRepository().update(jobOpt.get());

        Optional<Job> jobSavedOpt  = getJobRepository().findById(JOB_ID);

        Assertions.assertEquals(jobSavedOpt.get().getTitle(), jobOpt.get().getTitle(), "Job title should be altered");
        Assertions.assertEquals(jobSavedOpt.get().getTitle(), jobOpt.get().getTitle(), "Job title should be altered");
        Assertions.assertEquals(jobSavedOpt.get().getMinSalary(), jobOpt.get().getMinSalary(), "Job min salary should be altered");
        Assertions.assertEquals(jobSavedOpt.get().getMaxSalary(), jobOpt.get().getMaxSalary(), "Job max salary should be altered");
    }

    @Test
    @DisplayName("Saves Job")
    public void test04() {
        Job job = new Job(NEW_JOB_ID, "new job", 1000f, 2000f);
        getJobRepository().save(job);

        Optional<Job> jobOpt = getJobRepository().findById(NEW_JOB_ID);

        Assertions.assertTrue(jobOpt.isPresent(), "Job should be saved");
        Assertions.assertTrue(jobOpt.get().getTitle().contains("new"), "Job should be saved");
    }

    @Test
    @DisplayName("Finds all Jobs")
    public void test05() {
        Collection<Job> jobs = getJobRepository().findAll();
        Assertions.assertNotNull(jobs, "Jobs should be listed");
        Assertions.assertTrue(jobs.size() >= 19, "Jobs should be listed at all");
    }

    @Test
    @DisplayName("Finds for Job")
    public void test06() {
        Optional<Job> jobOpt = getJobRepository().findById(JOB_ID);
        Assertions.assertTrue(jobOpt.isPresent(), "Job should not be null");
    }

    @Test
    @DisplayName("Deletes Job")
    public void test07() {
        Optional<Job> jobOpt = getJobRepository().findById(NEW_JOB_ID);
        getJobRepository().deleteById(jobOpt.get().getId());
        boolean exists = getJobRepository().existsById(jobOpt.get().getId());
        Assertions.assertFalse(exists, "Job still existing");
    }

    @Test
    @DisplayName("Job exists")
    public void test08() {
        boolean exists = getJobRepository().existsById(JOB_ID);
        Assertions.assertTrue(exists, "Job should be existent");
    }

    @Test
    @DisplayName("Job doesn't exists")
    public void test09() {
        boolean exists = getJobRepository().existsById("NO_EXISTENT_JOB");
        Assertions.assertFalse(exists, "Job should not be existent");
    }

    @Test
    @DisplayName("Finds all Jobs Paginated")
    public void test10() {

        long count = getJobRepository().count();

        int pageSize = 1;

        for (int i = 1; i < count; i++) {

            int page = i * pageSize;

            Pagination pagination = new  Pagination(page, pageSize);

            Collection<Job> jobs = getJobRepository().findAll(pagination);
            Assertions.assertNotNull(jobs, "Jobs should be listed");
            Assertions.assertTrue(jobs.size() <= pageSize, "Jobs should be listed at all");

            new LinkedList<Job>(getJobRepository().findAll())
                    .subList(page, page + pageSize)
                    .forEach(c -> c.equals(jobs.iterator().next()));
        }
    }
}