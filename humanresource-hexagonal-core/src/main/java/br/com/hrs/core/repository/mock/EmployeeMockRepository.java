package br.com.hrs.core.repository.mock;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.EmployeeRepository;

import javax.inject.Named;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;

@Named
public class EmployeeMockRepository extends MockRepository<Employee, Integer> implements EmployeeRepository {

    @Override
    public Collection<Employee> buildCollection() {

        try {

                Employee employee01 = new Employee.Builder()
                        .id(100)
                        .firstName("Steven")
                        .lastName("King")
                        .email("SKING")
                        .phone("515.123.4567")
                        .hireDate(new SimpleDateFormat("dd.MM.yyyy").parse("17.06.2003"))
                        .job(new Job.Builder().id("AD_PRES").build())
                        .salary(24000f)
                        .commissionPercent(.1f)
                        .manager(new Employee.Builder().id(101).build())
                        .department(new Department.Builder().id(90).build())
                        .build();

                Employee employee02 = new Employee.Builder()
                        .id(101)
                        .firstName("Neena")
                        .lastName("Kochhar")
                        .email("NKOCHHAR")
                        .phone("515.123.4568")
                        .hireDate(new SimpleDateFormat("dd.MM.yyyy").parse("21.09.2005"))
                        .job(new Job.Builder().id("AD_VP").build())
                        .salary(17000f)
                        .commissionPercent(.2f)
                        .manager(new Employee.Builder().id(101).build())
                        .department(new Department.Builder().id(90).build())
                        .build();

            return Arrays.asList(employee01, employee02);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
