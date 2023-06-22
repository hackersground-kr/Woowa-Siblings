package net.azurewebsites.siren.domain.emergency.presentation.dto.api;

import lombok.Getter;

@Getter
public class EmergencyMessageApiDto {

    private String symTypCodMag;
    private String symTypCod;
    private String symOutDspYon;
    private String symOutDspMth;
    private String symBlkSttDtm;
    private String symBlkMsgTyp;
    private String symBlkMsg;
    private String symBlkEndDtm;
    private String rnum;
    private String hpid;
    private String emcOrgCod;
    private String dutyName;
    private String dutyAddr;

}
