package ir.phgint.domain.dto;

import java.util.Map;

public class MerchantProfileJsonResponce {
    private MerchantProfileDto merchantProfileDto;
    private boolean validated;
    private Map<String, String> errorMessages;

    public MerchantProfileDto getUserProfileDto() {
        return merchantProfileDto;
    }

    public void setUserProfileDto(MerchantProfileDto merchantProfileDto) {
        this.merchantProfileDto= merchantProfileDto;
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }
}
