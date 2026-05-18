package com.medical.medischeduler.prescription;

import java.util.List;

public interface PrescriptionService {
    List<Prescription> getAllPrescriptions();
    List<Prescription> getPrescriptionsByDoctor(Long doctorId);
    List<Prescription> getPrescriptionsByPatient(Long patientId);
    Prescription getPrescriptionById(Long id);
    Prescription savePrescription(Prescription prescription);
    void deletePrescription(Long id);
}
