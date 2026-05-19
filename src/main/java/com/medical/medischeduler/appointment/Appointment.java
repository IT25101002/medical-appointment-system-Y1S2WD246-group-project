package com.medical.medischeduler.appointment;

import com.medical.medischeduler.common.BaseEntity;
import com.medical.medischeduler.doctor.Doctor;
import com.medical.medischeduler.patient.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"doctor_id", "date", "time"})
})
public class Appointment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @NotNull(message = "Doctor must be selected")
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @NotNull(message = "Appointment date is required")
    @FutureOrPresent(message = "Appointment date must be today or in the future")
    @Column(nullable = false)
    private LocalDate date;

    @NotNull(message = "Appointment time is required")
    @Column(nullable = false)
    private LocalTime time;
    
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @NotBlank(message = "Reason for appointment is required")
    @Size(min = 5, max = 255, message = "Reason must be between 5 and 255 characters")
    @Column(nullable = false)
    private String reason;

    // Getters and Setters
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }
    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
