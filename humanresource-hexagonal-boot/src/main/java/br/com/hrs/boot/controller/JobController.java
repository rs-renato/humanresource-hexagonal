package br.com.hrs.boot.controller;

import br.com.hrs.core.model.Job;
import br.com.hrs.core.usecase.job.JobUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/job")
public class JobController {

    private JobUseCase jobUseCase;

    @Autowired
    public JobController(JobUseCase jobUseCase) {
        this.jobUseCase = jobUseCase;
    }

    @GetMapping("/{id}")
    public Job findById(@PathVariable String id){
        return jobUseCase.findById(id).get();
    }
}
