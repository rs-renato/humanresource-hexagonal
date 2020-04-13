package br.com.hrs.core.business.service;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Named;
import java.util.Objects;

@Named
public class EmployeePromotionService {

    private Logger logger = LogManager.getLogger(EmployeePromotionService.class);

    public void promote(Employee employee, Job job, Department department) {

        logger.info("Promoting employee '{}', to Job '{}' and Department '{}'", employee, job, department);

        if (Objects.isNull(employee)) {
            Error.of("Employee").when(FIELD.MANDATORY).trows();
        }

        if (Objects.isNull(job)) {
            Error.of("Job").when(FIELD.MANDATORY).trows();
        }

        if (Objects.isNull(department)) {
            Error.of("Department").when(FIELD.MANDATORY).trows();
        }

        employee.promotedTo(job, department);

        logger.info("Employee '{}' promoted!", employee.getId());
    }
}
