package com.medical.medischeduler.schedule;

import com.medical.medischeduler.common.BaseEntity;
import com.medical.medischeduler.doctor.Doctor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @NotNull(message = "Doctor is required")
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @NotNull(message = "Patient is required")
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private com.medical.medischeduler.patient.Patient patient;

    @NotNull(message = "Appointment is required")
    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private com.medical.medischeduler.appointment.Appointment appointment;

    @NotNull(message = "Schedule date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate date;

    @NotNull(message = "Start time is required")
    @DateTimeFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    @DateTimeFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime endTime;

    @NotNull(message = "Schedule status is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScheduleStatus status;

    // Getters and Setters
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public com.medical.medischeduler.patient.Patient getPatient() { return patient; }
    public void setPatient(com.medical.medischeduler.patient.Patient patient) { this.patient = patient; }
    public com.medical.medischeduler.appointment.Appointment getAppointment() { return appointment; }
    public void setAppointment(com.medical.medischeduler.appointment.Appointment appointment) { this.appointment = appointment; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public ScheduleStatus getStatus() { return status; }
    public void setStatus(ScheduleStatus status) { this.status = status; }

    // Aliases for user requirements
    public LocalDate getScheduledDate() { return date; }
    public void setScheduledDate(LocalDate date) { this.date = date; }
    public LocalTime getScheduledTime() { return startTime; }
    public void setScheduledTime(LocalTime time) { this.startTime = time; }
}
package com.medical.medischeduler.schedule;

import com.medical.medischeduler.common.BaseEntity;
import com.medical.medischeduler.doctor.Doctor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @NotNull(message = "Doctor is required")
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @NotNull(message = "Patient is required")
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private com.medical.medischeduler.patient.Patient patient;

    @NotNull(message = "Appointment is required")
    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private com.medical.medischeduler.appointment.Appointment appointment;

    @NotNull(message = "Schedule date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate date;

    @NotNull(message = "Start time is required")
    @DateTimeFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    @DateTimeFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime endTime;

    @NotNull(message = "Schedule status is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScheduleStatus status;

    // Getters and Setters
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public com.medical.medischeduler.patient.Patient getPatient() { return patient; }
    public void setPatient(com.medical.medischeduler.patient.Patient patient) { this.patient = patient; }
    public com.medical.medischeduler.appointment.Appointment getAppointment() { return appointment; }
    public void setAppointment(com.medical.medischeduler.appointment.Appointment appointment) { this.appointment = appointment; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public ScheduleStatus getStatus() { return status; }
    public void setStatus(ScheduleStatus status) { this.status = status; }

    // Aliases for user requirements
    public LocalDate getScheduledDate() { return date; }
    public void setScheduledDate(LocalDate date) { this.date = date; }
    public LocalTime getScheduledTime() { return startTime; }
    public void setScheduledTime(LocalTime time) { this.startTime = time; }
}
