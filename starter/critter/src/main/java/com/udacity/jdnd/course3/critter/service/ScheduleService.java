package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Schedule;

import java.util.List;

public interface ScheduleService {

    List<Schedule> getAllSchedules();
    List<Schedule> getScheduleByPetId(long petId);
    List<Schedule> getScheduleByEmployeeId(long employeeId);
    List<Schedule> getScheduleByCustomerId(long customerId);

}
