package net.azurewebsites.siren.domain.emergency.presentation.dto.response;

import lombok.Getter;
import net.azurewebsites.siren.domain.emergency.entity.AvailableBed;

@Getter
public class EmergencyRoomResponseDto {

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

    public EmergencyRoomResponseDto(AvailableBed availableBed) {
        this.hospitalName = availableBed.getHospitalName();
        this.telephone = availableBed.getTelephone();
        this.emergencyRoom = availableBed.getEmergencyRoom();
        this.operatingRoom = availableBed.getOperatingRoom();
        this.geIntensiveCareUnit = availableBed.getGeIntensiveCareUnit();
        this.thIntensiveCareUnit = availableBed.getThIntensiveCareUnit();
        this.intensiveCareUnit = availableBed.getIntensiveCareUnit();
        this.neurologicalIntensiveCareUnit = availableBed.getNeurologicalIntensiveCareUnit();
        this.neonatalIntensiveCareUnit = availableBed.getNeonatalIntensiveCareUnit();
        this.neurosurgeryIntensiveCareUnit = availableBed.getNeurosurgeryIntensiveCareUnit();
        this.inpatientRoom = availableBed.getInpatientRoom();
        this.surgicalInpatientRoom = availableBed.getSurgicalInpatientRoom();
        this.neurologyInpatientRoom = availableBed.getNeurologyInpatientRoom();
        this.imageUrl = availableBed.getImageUrl();
    }

}
