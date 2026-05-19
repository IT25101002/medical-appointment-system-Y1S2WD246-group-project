package com.medical.medischeduler.appointment;

import com.medical.medischeduler.doctor.Doctor;
import com.medical.medischeduler.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatient(Patient patient);
    List<Appointment> findByDoctor(Doctor doctor);
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long doctorId);
    boolean existsByDoctorAndDateAndTime(Doctor doctor, java.time.LocalDate date, java.time.LocalTime time);
}
