package com.medical.medischeduler.prescription;

import com.medical.medischeduler.appointment.Appointment;
import com.medical.medischeduler.common.BaseEntity;
import com.medical.medischeduler.doctor.Doctor;
import com.medical.medischeduler.patient.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "prescription")
public class Prescription extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = true)
    private Appointment appointment;

    @NotNull(message = "Doctor must be specified")
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @NotNull(message = "Patient must be specified")
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @NotBlank(message = "Medicine name is required")
    @Size(min = 2, max = 100, message = "Medicine name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String medicineName;

    @NotBlank(message = "Dosage instructions are required")
    @Size(min = 2, max = 100, message = "Dosage must be between 2 and 100 characters")
    @Column(nullable = false)
    private String dosage;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @NotBlank(message = "Diagnosis is required")
    @Size(min = 3, max = 200, message = "Diagnosis must be between 3 and 200 characters")
    @Column
    private String diagnosis;

    @Column(columnDefinition = "TEXT")
    private String notes;

    // Getters and Setters
    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public String getMedicineName() { return medicineName; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }
    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    // Aliases for Thymeleaf templates
    public String getMedicine() { return medicineName; }
    public String getDoctorName() { return doctor != null ? doctor.getFullName() : ""; }
    public String getStatus() { return "Ongoing"; }
}
