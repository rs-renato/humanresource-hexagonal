package br.com.hrs.persistence.repository.jdbc.rowmapper;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentRowMapper implements RowMapper<Department> {

    @Override
    public Department mapRow(ResultSet rs, int i) throws SQLException {
        return new Department.Builder()
                .id(rs.getInt("DEPARTMENT_ID"))
                .name(rs.getString("DEPARTMENT_NAME"))
                .manager(new Employee.Builder().id(rs.getInt("MANAGER_ID")).build())
                .location(new Location.Builder().id(rs.getInt("LOCATION_ID")).build())
            .build();
    }
}