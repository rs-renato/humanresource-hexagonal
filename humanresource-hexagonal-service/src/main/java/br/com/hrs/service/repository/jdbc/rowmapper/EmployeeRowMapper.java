package br.com.hrs.service.repository.jdbc.rowmapper;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee.Builder()
                .id(rs.getInt("EMPLOYEE_ID"))
                .firstName(rs.getString("FIRST_NAME"))
                .lastName(rs.getString("LAST_NAME"))
                .email(rs.getString("EMAIL"))
                .phone(rs.getString("PHONE_NUMBER"))
                .hireDate(rs.getDate("HIRE_DATE"))
                .job(new Job.Builder().id(rs.getString("JOB_ID")).build())
                .salary(rs.getFloat("SALARY"))
                .commissionPercent(rs.getFloat("COMMISSION_PCT"))
                .manager(new Employee.Builder().id(rs.getInt("MANAGER_ID")).build())
                .department(new Department.Builder().id(rs.getInt("DEPARTMENT_ID")).build())
            .build();
    }
}
