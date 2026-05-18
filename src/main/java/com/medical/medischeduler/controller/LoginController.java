package com.medical.medischeduler.controller;

import com.medical.medischeduler.admin.Admin;
import com.medical.medischeduler.admin.AdminRepository;
import com.medical.medischeduler.doctor.Doctor;
import com.medical.medischeduler.doctor.DoctorRepository;
import com.medical.medischeduler.patient.Patient;
import com.medical.medischeduler.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String firstName, @RequestParam String lastName,
                         @RequestParam String email, @RequestParam String phone,
                         @RequestParam String password, @RequestParam String address) {
        
        Patient existing = patientRepository.findByEmail(email);
        if (existing != null) {
            return "redirect:/signup?error=exists";
        }

        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setEmail(email);
        patient.setPhone(phone);
        patient.setPassword(password);
        patient.setAddress(address);
        
        patientRepository.save(patient);
        return "redirect:/login?registered=true";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, jakarta.servlet.http.HttpSession session) {
        // 1. Check Patient
        Patient patient = patientRepository.findByEmail(email);
        if (patient != null && password.equals(patient.getPassword())) {
            session.setAttribute("loggedInPatientEmail", email);
            session.setAttribute("userRole", "PATIENT");
            return "redirect:/patient/dashboard?email=" + email;
        }

        // 2. Check Doctor
        Doctor doctor = doctorRepository.findByEmail(email);
        if (doctor != null && password.equals(doctor.getPassword())) {
            session.setAttribute("loggedInDoctorEmail", email);
            session.setAttribute("userRole", "DOCTOR");
            return "redirect:/doctor/dashboard";
        }

        // 3. Check Admin
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null && password.equals(admin.getPassword())) {
            session.setAttribute("loggedInAdminEmail", email);
            session.setAttribute("userRole", "ADMIN");
            return "redirect:/admin/dashboard";
        }

        return "redirect:/login?error=true";
    }

    @GetMapping("/logout")
    public String logout(jakarta.servlet.http.HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login?logout=true";
    }
}
