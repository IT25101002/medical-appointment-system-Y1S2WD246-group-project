package com.medical.medischeduler.controller;

import com.medical.medischeduler.doctor.Doctor;
import com.medical.medischeduler.doctor.DoctorService;
import com.medical.medischeduler.schedule.Schedule;
import com.medical.medischeduler.schedule.ScheduleService;
import com.medical.medischeduler.schedule.ScheduleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/doctor/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private DoctorService doctorService;

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
    public String listSchedules(@RequestParam(required = false) Long doctorId,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                jakarta.servlet.http.HttpSession session, Model model) {
        Doctor loggedIn = getLoggedInDoctor(session);
        model.addAttribute("doctor", loggedIn);
        model.addAttribute("doctorName", loggedIn.getFullName());
        model.addAttribute("title", "Confirmed Schedules");
        model.addAttribute("activePage", "schedule");

        List<Schedule> schedules;
        if (doctorId != null && date != null) {
            schedules = scheduleService.getSchedulesByDoctorAndDate(doctorId, date);
        } else if (doctorId != null) {
            schedules = scheduleService.getSchedulesByDoctor(doctorId);
        } else if (date != null) {
            schedules = scheduleService.getSchedulesByDate(date);
        } else {
            schedules = scheduleService.getAllSchedules();
        }

        model.addAttribute("schedules", schedules);
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("selectedDoctorId", doctorId);
        model.addAttribute("selectedDate", date);
        return "schedule/list";
    }

    @GetMapping("/add")
    public String showAddForm(jakarta.servlet.http.HttpSession session, Model model) {
        Doctor loggedIn = getLoggedInDoctor(session);
        model.addAttribute("doctor", loggedIn);
        model.addAttribute("doctorName", loggedIn.getFullName());
        model.addAttribute("title", "Add Schedule");
        model.addAttribute("activePage", "schedule");

        model.addAttribute("scheduleObj", new Schedule());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "schedule/add";
    }

    @PostMapping("/save")
    public String saveSchedule(@ModelAttribute("scheduleObj") Schedule schedule) {
        scheduleService.saveSchedule(schedule);
        return "redirect:/doctor/schedules";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, jakarta.servlet.http.HttpSession session, Model model) {
        Doctor loggedIn = getLoggedInDoctor(session);
        model.addAttribute("doctor", loggedIn);
        model.addAttribute("doctorName", loggedIn.getFullName());
        model.addAttribute("title", "Edit Schedule");
        model.addAttribute("activePage", "schedule");

        Schedule schedule = scheduleService.getScheduleById(id);
        model.addAttribute("scheduleObj", schedule);
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "schedule/edit";
    }

    @PostMapping("/update/{id}")
    public String updateSchedule(@PathVariable Long id, @ModelAttribute("scheduleObj") Schedule schedule) {
        schedule.setId(id);
        scheduleService.saveSchedule(schedule);
        return "redirect:/doctor/schedules";
    }

    @GetMapping("/view/{id}")
    public String viewSchedule(@PathVariable Long id, jakarta.servlet.http.HttpSession session, Model model) {
        Doctor loggedIn = getLoggedInDoctor(session);
        model.addAttribute("doctor", loggedIn);
        model.addAttribute("doctorName", loggedIn.getFullName());
        model.addAttribute("title", "View Schedule");
        model.addAttribute("activePage", "schedule");

        Schedule schedule = scheduleService.getScheduleById(id);
        model.addAttribute("scheduleObj", schedule);
        return "schedule/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return "redirect:/doctor/schedules";
    }
}
