package com.medical.medischeduler.availability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Override
    public List<Availability> getAllAvailability() {
        return availabilityRepository.findAll();
    }

    @Override
    public List<Availability> getAvailabilityByDoctorId(Long doctorId) {
        return availabilityRepository.findByDoctorId(doctorId);
    }

    @Override
    public Availability getAvailabilityById(Long id) {
        return availabilityRepository.findById(id).orElse(null);
    }

    @Override
    public Availability saveAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }

    @Override
    public void deleteAvailability(Long id) {
        availabilityRepository.deleteById(id);
    }
}
