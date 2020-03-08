CREATE TABLE departments (
department_id INTEGER IDENTITY PRIMARY KEY NOT NULL,
department_name VARCHAR(100) NOT NULL,
manager_id INTEGER
);

CREATE TABLE jobs (
job_id VARCHAR(10) PRIMARY KEY NOT NULL,
job_title VARCHAR(100) NOT NULL,
min_salary DECIMAL(8,0),
max_salary DECIMAL(8,0));

CREATE TABLE employees (
employee_id INTEGER IDENTITY PRIMARY KEY NOT NULL,
status CHAR(1),
first_name VARCHAR(100),
email VARCHAR(100) NOT NULL,
job_id VARCHAR(10) NOT NULL,
salary DECIMAL(8,2) NOT NULL,
department_id INTEGER);

ALTER TABLE departments ADD FOREIGN KEY (manager_id) REFERENCES employees (employee_id);