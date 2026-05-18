package com.medical.medischeduler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        // You can keep dummy data for dashboard if needed
        model.addAttribute("upcomingCount", 2);
        model.addAttribute("prescriptionCount", 4);
        model.addAttribute("recordCount", 12);
        model.addAttribute("unpaidBills", 1);

        model.addAttribute("heartRate", 72);
        model.addAttribute("bloodPressure", "120/80");
        model.addAttribute("bloodSugar", 98);
        model.addAttribute("weight", 70);

        // Empty lists (avoid thymeleaf errors)
        model.addAttribute("appointments", new java.util.ArrayList<>());
        model.addAttribute("prescriptions", new java.util.ArrayList<>());
        model.addAttribute("messages", new java.util.ArrayList<>());
        model.addAttribute("appointmentDays", new java.util.ArrayList<>());

        return "dashboard";
    }
}