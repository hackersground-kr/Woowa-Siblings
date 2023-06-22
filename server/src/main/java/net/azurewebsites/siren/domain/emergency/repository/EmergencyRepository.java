package net.azurewebsites.siren.domain.emergency.repository;

import net.azurewebsites.siren.domain.emergency.entity.AvailableBed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyRepository extends JpaRepository<AvailableBed, String> {



}
