package com.medical.medischeduler.controller;

import com.medical.medischeduler.appointment.Appointment;
import com.medical.medischeduler.appointment.AppointmentService;
import com.medical.medischeduler.availability.AvailabilityService;
import com.medical.medischeduler.doctor.Doctor;
import com.medical.medischeduler.doctor.DoctorService;
import com.medical.medischeduler.patient.Patient;
import com.medical.medischeduler.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private com.medical.medischeduler.prescription.PrescriptionService prescriptionService;

    // ==============================
    // DASHBOARD
    // ==============================
    @GetMapping("/dashboard")
    public String patientDashboard(@RequestParam(required = false) String email, Model model) {

        if (email != null) {
            Patient patient = patientService.getAllPatients().stream()
                    .filter(p -> p.getEmail().equalsIgnoreCase(email))
                    .findFirst()
                    .orElse(null);

            if (patient != null) {
                model.addAttribute("patientName", patient.getFirstName() + " " + patient.getLastName());
                model.addAttribute("patientEmail", patient.getEmail());
            } else {
                model.addAttribute("patientName", "Valued Patient");
                model.addAttribute("patientEmail", email);
            }
        } else {
            model.addAttribute("patientName", "Valued Patient");
            model.addAttribute("patientEmail", "unknown@email.com");
        }

        // Dashboard counts
        model.addAttribute("upcomingCount", 0);
        model.addAttribute("prescriptionCount", 0);
        model.addAttribute("recordCount", 0);
        model.addAttribute("unpaidBills", 0);

        // Lists
        model.addAttribute("appointments", new ArrayList<>());
        model.addAttribute("prescriptions", new ArrayList<>());
        model.addAttribute("messages", new ArrayList<>());
        model.addAttribute("appointmentDays", new ArrayList<>());

        // Health overview sample data
        model.addAttribute("heartRate", 72);
        model.addAttribute("bloodPressure", "120/80");
        model.addAttribute("bloodSugar", 98);
        model.addAttribute("weight", 70);

        // IMPORTANT: This must match your HTML file location
        // templates/dashboard.html
        return "dashboard";
    }

    // ==============================
    // APPOINTMENTS PAGE
    // ==============================
    @GetMapping("/appointments")
    public String appointmentsPage(@RequestParam(required = false) String email,
                                   @RequestParam(required = false) Long doctorId,
                                   Model model) {
        Patient patient = null;
        if (email != null && !email.isEmpty()) {
            patient = patientService.getAllPatients().stream()
                    .filter(p -> p.getEmail().equalsIgnoreCase(email))
                    .findFirst()
                    .orElse(null);
        }
        if (patient == null && !patientService.getAllPatients().isEmpty()) {
            patient = patientService.getAllPatients().get(0);
        }

        if (patient != null) {
            model.addAttribute("patientName", patient.getFirstName() + " " + patient.getLastName());
            model.addAttribute("patientEmail", patient.getEmail());
            model.addAttribute("patient", patient);
            model.addAttribute("appointments", appointmentService.getAppointmentsByPatient(patient));
        } else {
            model.addAttribute("patientName", "Valued Patient");
            model.addAttribute("patientEmail", email != null ? email : "unknown@email.com");
            model.addAttribute("appointments", new ArrayList<>());
        }

        Appointment newAppt = new Appointment();
        if (doctorId != null) {
            Doctor d = doctorService.getDoctorById(doctorId);
            if (d != null) {
                newAppt.setDoctor(d);
            }
        }
        model.addAttribute("appointment", newAppt);
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("availabilities", availabilityService.getAllAvailability());
        model.addAttribute("title", "Book Appointment");
        model.addAttribute("selectedDoctorId", doctorId);

        return "patient/appointments";
    }

    // ==============================
    // DOCTORS PAGE
    // ==============================
    @GetMapping("/doctors")
    public String doctorsPage(@RequestParam(required = false) String email, Model model) {
        Patient patient = null;
        if (email != null && !email.isEmpty()) {
            patient = patientService.getAllPatients().stream()
                    .filter(p -> p.getEmail().equalsIgnoreCase(email))
                    .findFirst()
                    .orElse(null);
        }
        if (patient == null && !patientService.getAllPatients().isEmpty()) {
            patient = patientService.getAllPatients().get(0);
        }

        if (patient != null) {
            model.addAttribute("patientName", patient.getFirstName() + " " + patient.getLastName());
            model.addAttribute("patientEmail", patient.getEmail());
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patientName", "Valued Patient");
            model.addAttribute("patientEmail", email != null ? email : "unknown@email.com");
        }

        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("title", "Our Doctors & Specialists");

        return "patient/doctors";
    }

    // ==============================
    // BOOK APPOINTMENT POST
    // ==============================
    @PostMapping("/book-appointment")
    public String bookAppointmentFromPortal(@ModelAttribute @jakarta.validation.Valid Appointment appointment,
                                            org.springframework.validation.BindingResult bindingResult,
                                            @RequestParam(required = false) String patientEmail) {
        if (appointment.getPatient() == null && patientEmail != null && !patientEmail.isEmpty()) {
            Patient p = patientService.getAllPatients().stream()
                    .filter(pat -> pat.getEmail().equalsIgnoreCase(patientEmail))
                    .findFirst()
                    .orElse(null);
            appointment.setPatient(p);
        }

        if (bindingResult.hasErrors() && (bindingResult.getErrorCount() > 1 || !bindingResult.hasFieldErrors("patient") || appointment.getPatient() == null)) {
            String errorMsg = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return "redirect:/patient/appointments?email=" + (patientEmail != null ? patientEmail : "") + "&error=" + java.net.URLEncoder.encode(errorMsg, java.nio.charset.StandardCharsets.UTF_8);
        }

        try {
            appointmentService.createAppointment(appointment);
            return "redirect:/patient/appointments?email=" + (patientEmail != null ? patientEmail : "") + "&success=true";
        } catch (Exception e) {
            return "redirect:/patient/appointments?email=" + (patientEmail != null ? patientEmail : "") + "&error=" + java.net.URLEncoder.encode(e.getMessage(), java.nio.charset.StandardCharsets.UTF_8);
        }
    }

    // ==============================
    // PRESCRIPTIONS PAGE
    // ==============================
    @GetMapping("/prescriptions")
    public String prescriptionsPage(@RequestParam(required = false) String email, jakarta.servlet.http.HttpSession session, Model model) {
        String sessionEmail = (String) session.getAttribute("loggedInPatientEmail");
        String targetEmail = email != null ? email : sessionEmail;

        Patient patient = null;
        if (targetEmail != null && !targetEmail.isEmpty()) {
            patient = patientService.getAllPatients().stream()
                    .filter(p -> p.getEmail().equalsIgnoreCase(targetEmail))
                    .findFirst()
                    .orElse(null);
        }
        if (patient == null && !patientService.getAllPatients().isEmpty()) {
            patient = patientService.getAllPatients().get(0);
        }

        if (patient != null) {
            model.addAttribute("patientName", patient.getFirstName() + " " + patient.getLastName());
            model.addAttribute("patientEmail", patient.getEmail());
            model.addAttribute("patient", patient);

            List<com.medical.medischeduler.prescription.Prescription> all = prescriptionService.getPrescriptionsByPatient(patient.getId());
            model.addAttribute("ongoingPrescriptions", all);
            model.addAttribute("previousPrescriptions", new ArrayList<>());
        } else {
            model.addAttribute("patientName", "Valued Patient");
            model.addAttribute("patientEmail", targetEmail != null ? targetEmail : "unknown@email.com");
            model.addAttribute("ongoingPrescriptions", new ArrayList<>());
            model.addAttribute("previousPrescriptions", new ArrayList<>());
        }
        model.addAttribute("title", "My Prescriptions");
        return "patient/prescriptions";
    }

    // ==============================
    // MEDICAL RECORDS PAGE
    // ==============================
    @GetMapping("/records")
    public String recordsPage(Model model) {
        model.addAttribute("records", new ArrayList<>());
        return "patient/records";
    }

    // ==============================
    // BILLS PAGE
    // ==============================
    @GetMapping("/bills")
    public String billsPage(Model model) {
        model.addAttribute("bills", new ArrayList<>());
        return "patient/bills";
    }

    // ==============================
    // MESSAGES PAGE
    // ==============================
    @GetMapping("/messages")
    public String messagesPage(Model model) {
        model.addAttribute("messages", new ArrayList<>());
        return "patient/messages";
    }

    // ==============================
    // PROFILE PAGE
    @GetMapping("/profile")
    public String showProfile(@RequestParam(required = false) String email, jakarta.servlet.http.HttpSession session, Model model) {
        String targetEmail = email;
        if (targetEmail == null || targetEmail.isEmpty()) {
            targetEmail = (String) session.getAttribute("loggedInPatientEmail");
        }

        if (targetEmail == null) {
            return "redirect:/login";
        }

        final String finalEmail = targetEmail;
        Patient patient = patientService.getAllPatients().stream()
                .filter(p -> p.getEmail().equalsIgnoreCase(finalEmail))
                .findFirst()
                .orElse(null);

        if (patient != null) {
            model.addAttribute("patient", patient);
            model.addAttribute("patientName", patient.getFirstName() + " " + patient.getLastName());
            model.addAttribute("patientEmail", patient.getEmail());
            model.addAttribute("title", "Profile Settings");

            return "patient/profile";
        }

        return "redirect:/patient/dashboard";
    }

    // ==============================
    // UPDATE PROFILE
    // ==============================
    @PostMapping("/profile/update")
    public String updateSelfProfile(@ModelAttribute Patient patientUpdate,
                                    @RequestParam(required = false) String newPassword) {

        Patient existingPatient = patientService.getAllPatients().stream()
                .filter(p -> p.getId().equals(patientUpdate.getId()))
                .findFirst()
                .orElse(null);

        if (existingPatient != null) {
            existingPatient.setFirstName(patientUpdate.getFirstName());
            existingPatient.setLastName(patientUpdate.getLastName());
            existingPatient.setPhone(patientUpdate.getPhone());
            existingPatient.setAddress(patientUpdate.getAddress());
            
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                existingPatient.setPassword(newPassword);
            }

            patientService.savePatient(existingPatient);
            return "redirect:/patient/profile?email=" + existingPatient.getEmail() + "&success=true";
        }

        return "redirect:/patient/dashboard";
    }

    // ==============================
    // ADMIN / CRUD FUNCTIONS
    // ==============================

    // LIST ALL PATIENTS
    @GetMapping("/list")
    public String listPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patient/list";
    }

    // CREATE PATIENT FORM
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/create";
    }

    // SAVE NEW PATIENT
    @PostMapping("/save")
    public String savePatient(@ModelAttribute("patient") Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/patient/list";
    }

    // EDIT PATIENT FORM
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patient/edit";
    }

    // UPDATE PATIENT
    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable Long id,
                                @ModelAttribute("patient") Patient patient) {

        patient.setId(id);
        patientService.savePatient(patient);

        return "redirect:/patient/list";
    }

    // DELETE PATIENT
    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patient/list";
    }
}