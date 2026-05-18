package com.medical.medischeduler.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getSchedulesByDoctor(Long doctorId) {
        return scheduleRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Schedule> getSchedulesByDate(LocalDate date) {
        return scheduleRepository.findByDate(date);
    }

    @Override
    public List<Schedule> getSchedulesByDoctorAndDate(Long doctorId, LocalDate date) {
        return scheduleRepository.findByDoctorIdAndDate(doctorId, date);
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
