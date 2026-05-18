package com.medical.medischeduler.schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    List<Schedule> getAllSchedules();
    List<Schedule> getSchedulesByDoctor(Long doctorId);
    List<Schedule> getSchedulesByDate(LocalDate date);
    List<Schedule> getSchedulesByDoctorAndDate(Long doctorId, LocalDate date);
    Schedule getScheduleById(Long id);
    Schedule saveSchedule(Schedule schedule);
    void deleteSchedule(Long id);
}
