package net.azurewebsites.siren.domain.emergency.presentation;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import net.azurewebsites.siren.domain.emergency.presentation.dto.response.EmergencyCoordinateResponseDto;
import net.azurewebsites.siren.domain.emergency.presentation.dto.response.EmergencyRoomMessageResponseDto;
import net.azurewebsites.siren.domain.emergency.presentation.dto.response.EmergencyRoomResponseDto;
import net.azurewebsites.siren.domain.emergency.service.EmergencyService;
import net.azurewebsites.siren.global.response.DataResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emergency")
@RequiredArgsConstructor
@Tag(name = "Emergency", description = "응급 API Document")
public class EmergencyController {

    private final EmergencyService emergencyService;

    @GetMapping
    public ResponseEntity<DataResponse<List<EmergencyRoomResponseDto>>> getEmergencyRoomInfoList() {
        return DataResponse.ok("가용병상 정보 조회 성공", emergencyService.getEmergencyRoomInfoList());
    }

    @GetMapping("/coordinate")
    public ResponseEntity<DataResponse<List<EmergencyCoordinateResponseDto>>> getHospitalCordInfoList() {
        return DataResponse.ok("병원 좌표 조회 성공", emergencyService.getHospitalCordInfoList());
    }

    @GetMapping("/message")
    public ResponseEntity<DataResponse<List<EmergencyRoomMessageResponseDto>>> getEmergencyRoomMessageInfo(@NotBlank @RequestParam String hospitalCode) {
        return DataResponse.ok("응급실 메세지 조회 성공", emergencyService.getEmergencyRoomMessageInfo(hospitalCode));
    }

}
