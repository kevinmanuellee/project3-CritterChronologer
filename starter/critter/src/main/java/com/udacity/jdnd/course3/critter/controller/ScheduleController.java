package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.DTO.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = dtoToSchedule(scheduleDTO);
        schedule = scheduleService.saveSchedule(schedule);
        return scheduleToDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> scheduleList = scheduleService.getAllSchedules();
        List<ScheduleDTO> dtoList = new ArrayList<>();

        if(scheduleList.size() != 0){
            for(Schedule s : scheduleList){
                ScheduleDTO scheduleDTO = scheduleToDTO(s);
                dtoList.add(scheduleDTO);
            }
        }
        return dtoList;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> scheduleList = scheduleService.getScheduleByPetId(petId);
        List<ScheduleDTO> dtoList = new ArrayList<>();

        if(scheduleList.size() != 0){
            for(Schedule s : scheduleList){
                ScheduleDTO scheduleDTO = scheduleToDTO(s);
                dtoList.add(scheduleDTO);
            }
        }
        return dtoList;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> scheduleList = scheduleService.getScheduleByEmployeeId(employeeId);
        List<ScheduleDTO> dtoList = new ArrayList<>();

        if(scheduleList.size() != 0){
            for(Schedule s : scheduleList){
                ScheduleDTO scheduleDTO = scheduleToDTO(s);
                dtoList.add(scheduleDTO);
            }
        }
        return dtoList;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> scheduleList = scheduleService.getScheduleByCustomerId(customerId);
        List<ScheduleDTO> dtoList = new ArrayList<>();

        if(scheduleList.size() != 0){
            for(Schedule s : scheduleList){
                ScheduleDTO scheduleDTO = scheduleToDTO(s);
                dtoList.add(scheduleDTO);
            }
        }
        return dtoList;
    }

    private ScheduleDTO scheduleToDTO(Schedule schedule){

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        List<Pet> petList = petService.getAllPets();
        List<Employee> employeeList = employeeService.getAllEmployees();

        List<Long> petIds = new ArrayList<>();
        if(petList.size() != 0){
            for(Pet p : petList){
                petIds.add(p.getId());
            }
        }

        List<Long> employeeIds = new ArrayList<>();
        if(employeeList.size() != 0){
            for(Employee e : employeeList){
                employeeIds.add(e.getId());
            }
        }
        scheduleDTO.setPetIds(petIds);
        scheduleDTO.setEmployeeIds(employeeIds);

        return scheduleDTO;
    }

    private Schedule dtoToSchedule(ScheduleDTO scheduleDTO){

        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        List<Long> petIds = scheduleDTO.getPetIds();
        List<Long> employeeIds = scheduleDTO.getEmployeeIds();

        List<Pet> petList = new ArrayList<>();
        if(petIds.size() != 0){
            for(Long id : petIds){
                Pet pet = petService.getPet(id);
                petList.add(pet);
            }
        }

        List<Employee> employeeList = new ArrayList<>();
        if(petIds.size() != 0){
            for(Long id : employeeIds){
                Employee employee = employeeService.getEmployee(id);
                employeeList.add(employee);
            }
        }

        schedule.setEmployees(employeeList);
        schedule.setPets(petList);

        return schedule;
    }
}
