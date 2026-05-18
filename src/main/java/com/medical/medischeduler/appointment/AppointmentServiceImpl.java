package com.medical.medischeduler.appointment;

import com.medical.medischeduler.availability.Availability;
import com.medical.medischeduler.availability.AvailabilityRepository;
import com.medical.medischeduler.doctor.Doctor;
import com.medical.medischeduler.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private com.medical.medischeduler.schedule.ScheduleRepository scheduleRepository;

    @Override
    public Appointment createAppointment(Appointment appointment) {
        // Application level check 1: Check if appointment already exists for doctor + date + time
        if (appointmentRepository.existsByDoctorAndDateAndTime(appointment.getDoctor(), appointment.getDate(), appointment.getTime())) {
            throw new RuntimeException("This time slot is already booked. Please choose another slot.");
        }

        // Application level check 2: Check availability slots
        List<Availability> availabilities = availabilityRepository.findByDoctorId(appointment.getDoctor().getId());
        String dayOfWeek = appointment.getDate().getDayOfWeek().name();

        // Find the specific matching availability slot
        Availability matchingSlot = availabilities.stream()
                .filter(a -> a.getWorkingDay().equalsIgnoreCase(dayOfWeek) &&
                        !appointment.getTime().isBefore(a.getStartTime()) &&
                        !appointment.getTime().isAfter(a.getEndTime()))
                .findFirst()
                .orElse(null);

        if (matchingSlot == null) {
            throw new RuntimeException("Doctor is not available at the selected time.");
        }

        if (matchingSlot.isBooked() || "BOOKED".equalsIgnoreCase(matchingSlot.getStatus())) {
            throw new RuntimeException("This time slot is already booked. Please choose another slot.");
        }

        // Mark the time slot as booked / status BOOKED
        matchingSlot.setBooked(true);
        matchingSlot.setStatus("BOOKED");
        availabilityRepository.save(matchingSlot);

        appointment.setStatus(AppointmentStatus.PENDING);
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(Patient patient) {
        return appointmentRepository.findByPatient(patient);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(Doctor doctor) {
        return appointmentRepository.findByDoctor(doctor);
    }

    @Override
    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment updateStatus(Long appointmentId, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }

    @Override
    public void approveAppointment(Long id) {
        Appointment appointment = updateStatus(id, AppointmentStatus.APPROVED);

        // Prevent duplicate schedule creation for the same appointment
        if (!scheduleRepository.existsByAppointmentId(appointment.getId())) {
            com.medical.medischeduler.schedule.Schedule schedule = new com.medical.medischeduler.schedule.Schedule();
            schedule.setDoctor(appointment.getDoctor());
            schedule.setPatient(appointment.getPatient());
            schedule.setAppointment(appointment);
            schedule.setDate(appointment.getDate());
            schedule.setStartTime(appointment.getTime());
            schedule.setEndTime(appointment.getTime().plusHours(1));
            schedule.setStatus(com.medical.medischeduler.schedule.ScheduleStatus.CONFIRMED);
            scheduleRepository.save(schedule);
        }

        // Ensure schedule creation updates availability so the same slot cannot be booked again
        List<Availability> availabilities = availabilityRepository.findByDoctorId(appointment.getDoctor().getId());
        String dayOfWeek = appointment.getDate().getDayOfWeek().name();
        availabilities.stream()
                .filter(a -> a.getWorkingDay().equalsIgnoreCase(dayOfWeek) &&
                        !appointment.getTime().isBefore(a.getStartTime()) &&
                        !appointment.getTime().isAfter(a.getEndTime()))
                .findFirst()
                .ifPresent(a -> {
                    a.setBooked(true);
                    a.setStatus("BOOKED");
                    availabilityRepository.save(a);
                });
    }

    @Override
    public void rejectAppointment(Long id) {
        updateStatus(id, AppointmentStatus.REJECTED);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    private boolean isDoctorAvailable(Appointment appointment) {
        if (appointmentRepository.existsByDoctorAndDateAndTime(appointment.getDoctor(), appointment.getDate(), appointment.getTime())) {
            return false;
        }

        List<Availability> availabilities = availabilityRepository.findByDoctorId(appointment.getDoctor().getId());
        String dayOfWeek = appointment.getDate().getDayOfWeek().name();

        return availabilities.stream()
                .anyMatch(a -> a.getWorkingDay().equalsIgnoreCase(dayOfWeek) &&
                        !appointment.getTime().isBefore(a.getStartTime()) &&
                        !appointment.getTime().isAfter(a.getEndTime()) &&
                        !a.isBooked() &&
                        !"BOOKED".equalsIgnoreCase(a.getStatus()));
    }
}
