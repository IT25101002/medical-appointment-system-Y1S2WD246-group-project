package com.medical.medischeduler.config;

import com.medical.medischeduler.admin.Admin;
import com.medical.medischeduler.admin.AdminRepository;
import com.medical.medischeduler.availability.Availability;
import com.medical.medischeduler.availability.AvailabilityRepository;
import com.medical.medischeduler.doctor.Doctor;
import com.medical.medischeduler.doctor.DoctorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.util.Arrays;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(DoctorRepository doctorRepository, AvailabilityRepository availabilityRepository, AdminRepository adminRepository) {
        return args -> {
            if (adminRepository.count() == 0) {
                Admin admin = new Admin("admin@medischeduler.com", "admin123");
                adminRepository.save(admin);
                System.out.println("Database seeded with sample admin user.");
            }

            if (doctorRepository.count() == 0) {
                // Create Doctors
                Doctor drBrown = new Doctor();
                drBrown.setFirstName("Michael");
                drBrown.setLastName("Brown");
                drBrown.setEmail("dr.brown@example.com");
                drBrown.setPhone("+1 (555) 234-5678");
                drBrown.setSpecialty("Cardiologist");
                drBrown.setExperience("15 years");
                drBrown.setImage("dr_michael_brown.png");
                drBrown.setPassword("brown");
                drBrown = doctorRepository.save(drBrown);

                Doctor drJohnson = new Doctor();
                drJohnson.setFirstName("Sarah");
                drJohnson.setLastName("Johnson");
                drJohnson.setEmail("dr.johnson@example.com");
                drJohnson.setPhone("+1 (555) 987-6543");
                drJohnson.setSpecialty("Dermatologist");
                drJohnson.setExperience("10 years");
                drJohnson.setImage("dr_sarah_johnson.png");
                drJohnson.setPassword("johnson");
                drJohnson = doctorRepository.save(drJohnson);

                // Add Availabilities for Dr. Brown (Monday to Friday, 9-5)
                for (String day : Arrays.asList("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY")) {
                    Availability avail = new Availability();
                    avail.setDoctor(drBrown);
                    avail.setWorkingDay(day);
                    avail.setStartTime(LocalTime.of(9, 0));
                    avail.setEndTime(LocalTime.of(17, 0));
                    availabilityRepository.save(avail);
                }

                System.out.println("Database seeded with sample doctors and availability.");
            }
        };
    }
}
