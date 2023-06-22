package net.azurewebsites.siren.domain.emergency.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "available_bed")
public class AvailableBed {

    @Id
    private String hospitalCode;
    private String hospitalName;
    private String telephone;
    private String emergencyRoom;
    private String operatingRoom;
    private String geIntensiveCareUnit;
    private String thIntensiveCareUnit;
    private String intensiveCareUnit;
    private String neurologicalIntensiveCareUnit;
    private String neonatalIntensiveCareUnit;
    private String neurosurgeryIntensiveCareUnit;
    private String inpatientRoom;
    private String surgicalInpatientRoom;
    private String neurologyInpatientRoom;
    private String imageUrl;

}
