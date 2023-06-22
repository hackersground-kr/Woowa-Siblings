package net.azurewebsites.siren.domain.emergency.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EmergencyCoordinateResponseDto {

    private String hospitalCode;
    private String hospitalName;
    private String hospitalAddress;
    private String emergencyRoomTel;
    private double wgs84x;
    private double wgs84y;
    private String dutyEmclsName;
    private String dutyEmcls;

    @Builder
    public EmergencyCoordinateResponseDto(String hospitalCode, String hospitalName, String hospitalAddress, String emergencyRoomTel, double wgs84x, double wgs84y, String dutyEmclsName, String dutyEmcls) {
        this.hospitalCode = hospitalCode;
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.emergencyRoomTel = emergencyRoomTel;
        this.wgs84x = wgs84x;
        this.wgs84y = wgs84y;
        this.dutyEmclsName = dutyEmclsName;
        this.dutyEmcls = dutyEmcls;
    }

}
