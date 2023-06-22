package net.azurewebsites.siren.domain.emergency.presentation.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmergencyRoomMessageResponseDto {

    private String hospitalName;

    private String itemType;

    private String messageText;

    private String estimatedTime;

    @Builder
    public EmergencyRoomMessageResponseDto(String hospitalName, String itemType, String messageText, String estimatedTime) {
        this.hospitalName = hospitalName;
        this.itemType = itemType;
        this.messageText = messageText;
        this.estimatedTime = estimatedTime;
    }

}
