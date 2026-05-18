package com.medical.medischeduler.availability;

import java.util.List;

public interface AvailabilityService {
    List<Availability> getAllAvailability();
    List<Availability> getAvailabilityByDoctorId(Long doctorId);
    Availability getAvailabilityById(Long id);
    Availability saveAvailability(Availability availability);
    void deleteAvailability(Long id);
}
