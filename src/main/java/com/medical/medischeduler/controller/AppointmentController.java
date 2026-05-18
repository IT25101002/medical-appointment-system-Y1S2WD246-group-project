package com.medical.medischeduler.controller;

import com.medical.medischeduler.appointment.Appointment;
import com.medical.medischeduler.appointment.AppointmentService;
import com.medical.medischeduler.doctor.DoctorService;
import com.medical.medischeduler.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/book")
    public String showBookingForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("patients", patientService.getAllPatients()); // Temporary for testing
        return "appointment/book";
    }

    @PostMapping("/create")
    public String createAppointment(@ModelAttribute @jakarta.validation.Valid Appointment appointment, org.springframework.validation.BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return "redirect:/appointment/book?error=" + java.net.URLEncoder.encode(errorMsg, java.nio.charset.StandardCharsets.UTF_8);
        }
        try {
            appointmentService.createAppointment(appointment);
            return "redirect:/appointment/patient/" + appointment.getPatient().getId();
        } catch (Exception e) {
            return "redirect:/appointment/book?error=" + java.net.URLEncoder.encode(e.getMessage(), java.nio.charset.StandardCharsets.UTF_8);
        }
    }

    @GetMapping("/patient/{id}")
    public String viewPatientAppointments(@PathVariable Long id, Model model) {
        model.addAttribute("appointments", appointmentService.getAppointmentsByPatientId(id));
        return "appointment/patient-list";
    }

    @GetMapping("/doctor/{id}")
    public String viewDoctorAppointments(@PathVariable Long id, Model model) {
        model.addAttribute("appointments", appointmentService.getAppointmentsByDoctorId(id));
        return "appointment/doctor-list";
    }

    @GetMapping("/approve/{id}")
    public String approveAppointment(@PathVariable Long id) {
        appointmentService.approveAppointment(id);
        return "redirect:/doctor/dashboard";
    }

    @GetMapping("/reject/{id}")
    public String rejectAppointment(@PathVariable Long id) {
        appointmentService.rejectAppointment(id);
        return "redirect:/doctor/dashboard";
    }

    @GetMapping("/list")
    public String listAllAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "appointment/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointment/list";
    }
}
