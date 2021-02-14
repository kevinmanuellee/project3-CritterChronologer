package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.Entity;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee {
    private long id;
    private String name;
    private Set<EmployeeSkill> skills;
    private Set<DayOfWeek> daysAvailable;
}
