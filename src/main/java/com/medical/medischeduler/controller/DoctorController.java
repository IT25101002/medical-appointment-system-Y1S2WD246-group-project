package com.medical.medischeduler.controller;

import com.medical.medischeduler.doctor.Doctor;
import com.medical.medischeduler.doctor.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.medical.medischeduler.appointment.Appointment;
import com.medical.medischeduler.appointment.AppointmentService;
import com.medical.medischeduler.patient.Patient;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

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

    @GetMapping("/dashboard")
    public String doctorDashboard(jakarta.servlet.http.HttpSession session, Model model) {
        Doctor doctor = getLoggedInDoctor(session);
        model.addAttribute("doctor", doctor);
        model.addAttribute("doctorName", doctor.getFullName());
        model.addAttribute("title", "Doctor Dashboard");
        model.addAttribute("activePage", "dashboard");

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctor.getId());
        List<Patient> patients = appointments.stream()
                .map(Appointment::getPatient)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("totalAppointments", appointments.size());
        model.addAttribute("totalPatients", patients.size());
        model.addAttribute("appointments", appointments);
        model.addAttribute("patients", patients);

        return "doctor/dashboard";
    }

    @GetMapping("/appointments")
    public String doctorAppointments(jakarta.servlet.http.HttpSession session, Model model) {
        Doctor doctor = getLoggedInDoctor(session);
        model.addAttribute("doctor", doctor);
        model.addAttribute("doctorName", doctor.getFullName());
        model.addAttribute("title", "Appointments");
        model.addAttribute("activePage", "appointments");

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctor.getId());
        model.addAttribute("appointments", appointments);

        return "doctor/appointments";
    }

    @GetMapping("/appointments/approve/{id}")
    public String approveAppointment(@PathVariable Long id) {
        appointmentService.approveAppointment(id);
        return "redirect:/doctor/appointments";
    }

    @GetMapping("/appointments/reject/{id}")
    public String rejectAppointment(@PathVariable Long id) {
        appointmentService.rejectAppointment(id);
        return "redirect:/doctor/appointments";
    }

    @GetMapping("/patients")
    public String doctorPatients(jakarta.servlet.http.HttpSession session, Model model) {
        Doctor doctor = getLoggedInDoctor(session);
        model.addAttribute("doctor", doctor);
        model.addAttribute("doctorName", doctor.getFullName());
        model.addAttribute("title", "Patient List");
        model.addAttribute("activePage", "patients");

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctor.getId());
        List<Patient> patients = appointments.stream()
                .map(Appointment::getPatient)
                .distinct()
                .collect(Collectors.toList());
        model.addAttribute("patients", patients);

        return "doctor/patients";
    }

    @GetMapping("/schedule")
    public String doctorSchedule(jakarta.servlet.http.HttpSession session, Model model) {
        Doctor doctor = getLoggedInDoctor(session);
        model.addAttribute("doctor", doctor);
        model.addAttribute("doctorName", doctor.getFullName());
        model.addAttribute("title", "Schedule & Availability");
        model.addAttribute("activePage", "schedule");
        return "doctor/schedule";
    }

    @GetMapping("/profile")
    public String doctorProfile(jakarta.servlet.http.HttpSession session, Model model) {
        Doctor doctor = getLoggedInDoctor(session);
        model.addAttribute("doctor", doctor);
        model.addAttribute("doctorName", doctor.getFullName());
        model.addAttribute("title", "My Profile");
        model.addAttribute("activePage", "profile");
        return "doctor/profile";
    }

    @PostMapping("/profile/update")
    public String updateDoctorProfile(jakarta.servlet.http.HttpSession session, @ModelAttribute Doctor doctor, @RequestParam(required = false) String newPassword) {
        if (newPassword != null && !newPassword.isEmpty()) {
            doctor.setPassword(newPassword);
        } else {
            Doctor existing = doctorService.getDoctorById(doctor.getId());
            if (existing != null) {
                doctor.setPassword(existing.getPassword());
            }
        }
        doctorService.saveDoctor(doctor);
        session.setAttribute("loggedInDoctorEmail", doctor.getEmail());
        return "redirect:/doctor/profile?success=true";
    }

    @GetMapping("/list")
    public String listDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctor/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctor/create";
    }

    @PostMapping("/save")
    public String saveDoctor(@Valid @ModelAttribute("doctor") Doctor doctor,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "doctor/create";
        }
        doctorService.saveDoctor(doctor);
        return "redirect:/doctor/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);
        model.addAttribute("doctor", doctor);
        return "doctor/edit";
    }

    @PostMapping("/update/{id}")
    public String updateDoctor(@PathVariable Long id,
                               @Valid @ModelAttribute("doctor") Doctor doctor,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            doctor.setId(id);
            return "doctor/edit";
        }

        // Preserve existing password if left blank
        Doctor existing = doctorService.getDoctorById(id);
        if (existing != null) {
            if (doctor.getPassword() == null || doctor.getPassword().isBlank()) {
                doctor.setPassword(existing.getPassword());
            }
        }

        doctor.setId(id);
        doctorService.saveDoctor(doctor);
        return "redirect:/doctor/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctor/list";
    }
}
