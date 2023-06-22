package net.azurewebsites.siren.domain.emergency.presentation.dto.api;

import lombok.Getter;

@Getter
public class EmergencyCoordinateApiDto {

    private double wgs84Lon;
    private double wgs84Lat;
    private String rnum;
    private String phpid;
    private String hpid;
    private String dutyTel3;
    private String dutyTel1;
    private String dutyName;
    private String dutyEmclsName;
    private String dutyEmcls;
    private String dutyAddr;

}
