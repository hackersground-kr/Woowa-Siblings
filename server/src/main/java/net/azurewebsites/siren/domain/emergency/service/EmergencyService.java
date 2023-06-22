package net.azurewebsites.siren.domain.emergency.service;

import net.azurewebsites.siren.domain.emergency.presentation.dto.response.EmergencyCoordinateResponseDto;
import net.azurewebsites.siren.domain.emergency.presentation.dto.response.EmergencyRoomMessageResponseDto;
import net.azurewebsites.siren.domain.emergency.presentation.dto.response.EmergencyRoomResponseDto;

import java.util.List;

public interface EmergencyService {

    List<EmergencyRoomResponseDto> getEmergencyRoomInfoList();

    List<EmergencyCoordinateResponseDto> getHospitalCordInfoList();

    List<EmergencyRoomMessageResponseDto> getEmergencyRoomMessageInfo(String hospitalCode);

}
