package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.DTO.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);
    Employee getEmployee(long employeeId);
    void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId);
    List<Employee> getEmployeesForService(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek);
}
