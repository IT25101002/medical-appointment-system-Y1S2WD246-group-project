package com.medical.medischeduler.controller;

import com.medical.medischeduler.appointment.AppointmentService;
import com.medical.medischeduler.doctor.Doctor;
import com.medical.medischeduler.doctor.DoctorService;
import com.medical.medischeduler.patient.PatientService;
import com.medical.medischeduler.prescription.Prescription;
import com.medical.medischeduler.prescription.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctor/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    private Doctor getLoggedInDoctor(jakarta.servlet.http.HttpSession session) {
        String email = (String) session.getAttribute("loggedInDoctorEmail");
        Doctor doctor = null;
        if (email != null && !email.isEmpty()) {
            doctor = doctorService.getAllDoctors().stream()
                    .filter(d -> d.getEmail() != null && d.getEmail().equalsIgnoreCase(email))
                    .findFirst()
                    .orElse(null);
        }
        if (doctor == null && !doctorService.getAllDoctors().isEmpty()) {
            doctor = doctorService.getAllDoctors().get(0);
        }
        if (doctor == null) {
            doctor = new Doctor();
            doctor.setFirstName("Michael");
            doctor.setLastName("Brown");
            doctor.setEmail("dr.brown@example.com");
            doctor.setSpecialty("Cardiologist");
            doctor.setExperience("15 years");
            doctor.setPhone("+1 (555) 234-5678");
            doctor.setPassword("brown");
        }
        return doctor;
    }

    @GetMapping
    public String listPrescriptions(jakarta.servlet.http.HttpSession session, Model model) {
        Doctor loggedIn = getLoggedInDoctor(session);
        model.addAttribute("doctor", loggedIn);
        model.addAttribute("doctorName", loggedIn.getFullName());
        model.addAttribute("title", "Prescriptions");
        model.addAttribute("activePage", "prescriptions");

        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
        model.addAttribute("prescriptions", prescriptions);
        return "prescription/list";
    }

    @GetMapping("/add")
    public String showAddForm(jakarta.servlet.http.HttpSession session, Model model) {
        Doctor loggedIn = getLoggedInDoctor(session);
        model.addAttribute("doctor", loggedIn);
        model.addAttribute("doctorName", loggedIn.getFullName());
        model.addAttribute("title", "Add Prescription");
        model.addAttribute("activePage", "prescriptions");

        model.addAttribute("prescriptionObj", new Prescription());
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("patients", patientService.getAllPatients());
        return "prescription/add";
    }

    @PostMapping("/save")
    public String savePrescription(@ModelAttribute("prescriptionObj") Prescription prescription) {
        prescriptionService.savePrescription(prescription);
        return "redirect:/doctor/prescriptions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, jakarta.servlet.http.HttpSession session, Model model) {
        Doctor loggedIn = getLoggedInDoctor(session);
        model.addAttribute("doctor", loggedIn);
        model.addAttribute("doctorName", loggedIn.getFullName());
        model.addAttribute("title", "Edit Prescription");
        model.addAttribute("activePage", "prescriptions");

        Prescription prescription = prescriptionService.getPrescriptionById(id);
        model.addAttribute("prescriptionObj", prescription);
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("patients", patientService.getAllPatients());
        return "prescription/edit";
    }

    @PostMapping("/update/{id}")
    public String updatePrescription(@PathVariable Long id, @ModelAttribute("prescriptionObj") Prescription prescription) {
        prescription.setId(id);
        prescriptionService.savePrescription(prescription);
        return "redirect:/doctor/prescriptions";
    }

    @GetMapping("/view/{id}")
    public String viewPrescription(@PathVariable Long id, jakarta.servlet.http.HttpSession session, Model model) {
        Doctor loggedIn = getLoggedInDoctor(session);
        model.addAttribute("doctor", loggedIn);
        model.addAttribute("doctorName", loggedIn.getFullName());
        model.addAttribute("title", "View Prescription");
        model.addAttribute("activePage", "prescriptions");

        Prescription prescription = prescriptionService.getPrescriptionById(id);
        model.addAttribute("prescriptionObj", prescription);
        return "prescription/view";
    }

    @GetMapping("/delete/{id}")
    public String deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return "redirect:/doctor/prescriptions";
    }
}
