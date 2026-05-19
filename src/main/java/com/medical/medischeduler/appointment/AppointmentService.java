package com.medical.medischeduler.appointment;

import com.medical.medischeduler.doctor.Doctor;
import com.medical.medischeduler.patient.Patient;

import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);
    List<Appointment> getAppointmentsByPatient(Patient patient);
    List<Appointment> getAppointmentsByDoctor(Doctor doctor);
    List<Appointment> getAppointmentsByPatientId(Long patientId);
    List<Appointment> getAppointmentsByDoctorId(Long doctorId);
    List<Appointment> getAllAppointments();
    Appointment updateStatus(Long appointmentId, AppointmentStatus status);
    void approveAppointment(Long id);
    void rejectAppointment(Long id);
    void deleteAppointment(Long id);
    Appointment getAppointmentById(Long id);
}
