package com.medical.medischeduler.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByDoctorId(Long doctorId);
    List<Schedule> findByDate(LocalDate date);
    List<Schedule> findByDoctorIdAndDate(Long doctorId, LocalDate date);
    boolean existsByAppointmentId(Long appointmentId);
    Schedule findByAppointmentId(Long appointmentId);
}
