package net.azurewebsites.siren.domain.emergency.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.azurewebsites.siren.domain.emergency.presentation.dto.api.EmergencyCoordinateApiDto;
import net.azurewebsites.siren.domain.emergency.presentation.dto.api.EmergencyMessageApiDto;
import net.azurewebsites.siren.domain.emergency.presentation.dto.response.EmergencyCoordinateResponseDto;
import net.azurewebsites.siren.domain.emergency.presentation.dto.response.EmergencyRoomMessageResponseDto;
import net.azurewebsites.siren.domain.emergency.presentation.dto.response.EmergencyRoomResponseDto;
import net.azurewebsites.siren.domain.emergency.repository.EmergencyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmergencyServiceImpl implements EmergencyService {

    private final WebClient webClient;

    private final EmergencyRepository emergencyRepository;

    @Override
    public List<EmergencyRoomResponseDto> getEmergencyRoomInfoList() {

        return emergencyRepository.findAll().stream()
                .map(EmergencyRoomResponseDto::new)
                .toList();
    }

    @Override
    public List<EmergencyCoordinateResponseDto> getHospitalCordInfoList() {

        EmergencyCoordinateApiDto[] response = webClient.get()
                .uri("/HttpTrigger2?code=wHE6fpgb4MmCkRV-n-cnKrvKe7PJtp-zTnu5DjIj9BTOAzFuJXKH7A==")
                .retrieve()
                .bodyToMono(EmergencyCoordinateApiDto[].class)
                .block();

        if (response != null) {

            List<EmergencyCoordinateResponseDto> transformedList = new ArrayList<>();

            for (EmergencyCoordinateApiDto dto : response) {

                transformedList.add(
                        EmergencyCoordinateResponseDto.builder()
                                .hospitalCode(dto.getHpid())
                                .hospitalName(dto.getDutyName())
                                .hospitalAddress(dto.getDutyAddr())
                                .emergencyRoomTel(dto.getDutyTel3())
                                .wgs84x(dto.getWgs84Lat())
                                .wgs84y(dto.getWgs84Lon())
                                .dutyEmclsName(dto.getDutyEmclsName())
                                .dutyEmcls(dto.getDutyEmcls())
                                .build()
                );
            }

            return transformedList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<EmergencyRoomMessageResponseDto> getEmergencyRoomMessageInfo(String hospitalCode) {

        EmergencyMessageApiDto[] response = webClient.get()
                .uri("/HttpTrigger1?code=_dWT6HtC7raSuuq9BV0kVb8GEBKulh06C9yusfPNli79AzFu-rNVWg==&hospitalCode=" + hospitalCode)
                .retrieve()
                .bodyToMono(EmergencyMessageApiDto[].class)
                .block();

        if (response != null) {

            List<EmergencyRoomMessageResponseDto> transformedList = new ArrayList<>();

            for (EmergencyMessageApiDto dto : response) {

                String startDate = formattingDateTime(dto.getSymBlkSttDtm());
                String endDate = formattingDateTime(dto.getSymBlkEndDtm());

                transformedList.add(
                        EmergencyRoomMessageResponseDto.builder()
                                .hospitalName(dto.getDutyName())
                                .itemType(dto.getSymBlkMsgTyp())
                                .messageText(dto.getSymBlkMsg())
                                .estimatedTime(startDate + " ~ " + endDate)
                                .build()
                );
            }

            return transformedList;
        } else {
            return Collections.emptyList();
        }

    }

    public String formattingDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("uuuuMMddHHmmss"))
                .format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
    }

}
