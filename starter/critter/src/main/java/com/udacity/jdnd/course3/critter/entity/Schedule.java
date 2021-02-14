package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pet> pets;
    
    private LocalDate date;
    private Set<EmployeeSkill> activities;


}
