package com.medical.medischeduler.controller;

import com.medical.medischeduler.doctor.DoctorService;
import com.medical.medischeduler.patient.PatientService;
import com.medical.medischeduler.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("doctorCount", doctorService.getAllDoctors().size());
        model.addAttribute("patientCount", patientService.getAllPatients().size());
        model.addAttribute("appointmentCount", appointmentService.getAllAppointments().size());
        model.addAttribute("title", "Admin Control Center");
        return "admin/dashboard";
    }
}
