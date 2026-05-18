package com.medical.medischeduler.availability;

import com.medical.medischeduler.common.BaseEntity;
import com.medical.medischeduler.doctor.Doctor;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalTime;

@Entity
@Table(name = "availabilities")
public class Availability extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private String workingDay;
    private LocalTime startTime;
    private LocalTime endTime;

    private boolean booked = false;
    private String status = "AVAILABLE"; // AVAILABLE or BOOKED

    // Getters and Setters
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public String getWorkingDay() { return workingDay; }
    public void setWorkingDay(String workingDay) { this.workingDay = workingDay; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public boolean isBooked() { return booked; }
    public void setBooked(boolean booked) { this.booked = booked; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
