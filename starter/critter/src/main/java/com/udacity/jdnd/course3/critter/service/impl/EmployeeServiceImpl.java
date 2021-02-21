package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.DTO.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(long employeeId) {
        Employee employee;
        Optional<Employee> byId = employeeRepository.findById(employeeId);
        if(byId.isPresent()){
            employee = byId.get();
            return employee;
        } else {
            throw new NoSuchElementException("Employee with id:" + employeeId + " can not be found");
        }
    }

    @Override
    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee;
        Optional<Employee> byId = employeeRepository.findById(employeeId);
        if(byId.isPresent()){
            employee = byId.get();
            employee.setDaysAvailable(daysAvailable);
            employeeRepository.save(employee);
        } else {
            throw new NoSuchElementException("Employee with id:" + employeeId + " can not be found");
        }
    }

    @Override
    public List<Employee> getEmployeesForService(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {
        List<Employee> employees = new ArrayList<>();
        List<Employee> employeesAvailable = employeeRepository.findAllByDaysAvailable(dayOfWeek);
        for (Employee e : employeesAvailable){
            if(e.getSkills().contains(skills)){
                employees.add(e);
            }
        }
        return employees;
    }
}
