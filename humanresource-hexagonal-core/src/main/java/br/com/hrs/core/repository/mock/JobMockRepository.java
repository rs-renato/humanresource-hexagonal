package br.com.hrs.core.repository.mock;

import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.JobRepository;

import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

@Named
public class JobMockRepository extends MockRepository<Job, String> implements JobRepository {

    @Override
    public List<Job> buildCollection() {

        Job job01 = new Job.Builder()
                        .id("AD_PRES")
                        .title("President")
                        .minSalary(20080f)
                        .maxSalay(40000f)
                     .build();

        Job job02 = new Job.Builder()
                .id("AD_VP")
                .title("Administration Vice President")
                .minSalary(15000f)
                .maxSalay(30000f)
                .build();


        return Arrays.asList(job01, job02);
    }
}
