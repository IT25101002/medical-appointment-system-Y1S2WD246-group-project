package com.medical.medischeduler.controller;

import com.medical.medischeduler.availability.Availability;
import com.medical.medischeduler.availability.AvailabilityService;
import com.medical.medischeduler.doctor.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/list")
    public String listAvailability(Model model) {
        List<Availability> availabilities = availabilityService.getAllAvailability();
        model.addAttribute("availabilities", availabilities);
        return "availability/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("availability", new Availability());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "availability/add";
    }

    @PostMapping("/save")
    public String saveAvailability(@ModelAttribute("availability") Availability availability) {
        availabilityService.saveAvailability(availability);
        return "redirect:/availability/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Availability availability = availabilityService.getAvailabilityById(id);
        model.addAttribute("availability", availability);
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "availability/edit";
    }

    @PostMapping("/update/{id}")
    public String updateAvailability(@PathVariable Long id, @ModelAttribute("availability") Availability availability) {
        availability.setId(id);
        availabilityService.saveAvailability(availability);
        return "redirect:/availability/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAvailability(@PathVariable Long id) {
        availabilityService.deleteAvailability(id);
        return "redirect:/availability/list";
    }
}
