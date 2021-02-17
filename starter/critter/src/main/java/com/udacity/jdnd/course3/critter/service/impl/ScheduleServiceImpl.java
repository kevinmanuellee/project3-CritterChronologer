package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getScheduleByPetId(long petId) {
        return scheduleRepository.findAllByPetId(petId);
    }

    @Override
    public List<Schedule> getScheduleByEmployeeId(long employeeId) {
        return scheduleRepository.findAllByEmployeeId(employeeId);
    }

    @Override
    public List<Schedule> getScheduleByCustomerId(long customerId) {
        return scheduleRepository.findAllByCustomerId(customerId);
    }
}
