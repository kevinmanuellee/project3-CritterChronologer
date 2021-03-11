package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getScheduleByPetId(long petId) {
        return scheduleRepository.findAllByPetsId(petId);
    }

    @Override
    public List<Schedule> getScheduleByEmployeeId(long employeeId) {
        return scheduleRepository.findAllByEmployeesId(employeeId);
    }

    @Override
    public List<Schedule> getScheduleByCustomerId(long customerId) {
        return scheduleRepository.findAllByCustomerId(customerId);
    }
}
