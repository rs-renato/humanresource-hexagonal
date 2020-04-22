package br.com.hrs.service.repository.jdbc.rowmapper;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentRowMapper implements RowMapper<Department> {

    @Override
    public Department mapRow(ResultSet rs, int i) throws SQLException {

        Integer id = rs.getInt(1);
        String name = rs.getString(2);
        Integer managerId = rs.getInt(3);
        Integer locationId = rs.getInt(4);

        return new Department(
                id, name,
                new Employee.Builder().id(managerId).build(),
                new Location.Builder().id(locationId).build());
    }
}