package br.com.hrs.core.validator.employee;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.validator.department.ExistentDemartmentValidator;
import br.com.hrs.core.validator.job.ExistentJobValidator;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Objects;

@Named
public class PromoteEmployeeValidator implements PromotionValidator {

    private ExistentJobValidator existentJobValidator;
    private ExistentDemartmentValidator existentDemartmentValidator;

    @Inject
    public PromoteEmployeeValidator(ExistentJobValidator existentJobValidator, ExistentDemartmentValidator existentDemartmentValidator) {
        this.existentJobValidator = existentJobValidator;
        this.existentDemartmentValidator = existentDemartmentValidator;
    }

    @Override
    public void validate(Employee employee, Job job, Department department) {

        if (Objects.isNull(employee)) {
            Error.of("Employee").when(FIELD.MANDATORY).trows();
        }

        if (Objects.isNull(job)) {
            Error.of("Job").when(FIELD.MANDATORY).trows();
        }

        if (Objects.isNull(department)) {
            Error.of("Department").when(FIELD.MANDATORY).trows();
        }

        this.existentDemartmentValidator.validate(department);
        this.existentJobValidator.validate(job);


        LocalDate hireDate = employee.getHireDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = Calendar.getInstance().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // cannot be promoted if hiredate is less than one year
        if (ChronoUnit.YEARS.between(hireDate, today) < 1){
            throw new HrsBusinessException(String.format("Employee cannot be promoted because hidedate is too early"));
        }
    }
}
