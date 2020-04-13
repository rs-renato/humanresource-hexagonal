package br.com.hrs.core.model;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;

import java.util.Objects;

public class Employee {

    public enum STATUS {
        ADMITTED(1),
        PROMOTED(2),
        FIRED(3);

        private int code;

        STATUS(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    private Integer id;
    private String name;
    private String email;
    private Float salary;
    private Job job;
    private Department department;
    private STATUS status;

    public Employee(Integer id, String email, Float salary, Job job, Department department) {
        this.id = id;
        this.email = email;
        this.salary = salary;
        this.job = job;
        this.department = department;
        this.status = STATUS.ADMITTED;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public void promotedTo(Job job, Department department) {

        if (Objects.isNull(job)) {
            Error.of("Job").when(FIELD.MANDATORY).on("promotion").trows();
        }

        if (Objects.isNull(department)) {
            Error.of("Department").when(FIELD.MANDATORY).on("promotion").trows();
        }

        setStatus(STATUS.PROMOTED);
        setSalary(job.getMinSalary());
        setDepartment(department);
        setJob(job);
    }

    public void fired() {
        this.status = STATUS.FIRED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", job=" + job +
                ", department=" + department +
                ", status=" + status +
                '}';
    }
}