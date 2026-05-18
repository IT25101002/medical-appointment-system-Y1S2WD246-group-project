package com.medical.medischeduler.prescription;

import com.medical.medischeduler.appointment.Appointment;
import com.medical.medischeduler.common.BaseEntity;
import com.medical.medischeduler.doctor.Doctor;
import com.medical.medischeduler.patient.Patient;
import jakarta.persistence.*;

@Entity
@Table(name = "prescription")
public class Prescription extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = true)
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(nullable = false)
    private String medicineName;

    @Column(nullable = false)
    private String dosage;

    @Column(columnDefinition = "TEXT")
    private String instructions;

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
