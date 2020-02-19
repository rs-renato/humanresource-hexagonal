package br.com.hrs.core.service;

import br.com.hrs.core.error.Validations;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.port.outbound.DepartmentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Named;
import java.util.Objects;

@Named
public class EmployeePromotionService {

    Logger logger = LogManager.getLogger(EmployeePromotionService.class);

    public void promote(Employee employee, Job job, Department department){

        logger.info("Promoting employee '{}', to Job '{}' and Department '{}'", employee, job, department);

        if (Objects.isNull(employee)){
            Validations.mandatory("Employee");
        }


        if (Objects.isNull(job)){
            Validations.mandatory("Job");
        }

        if (Objects.isNull(department)){
            Validations.mandatory("Department");
        }

        employee.setSalary(job.getMinSalary());
        employee.setJob(job);
        employee.setDepartment(department);

        logger.info("Employee '{}' promoted!", employee.getId());
    }
}
