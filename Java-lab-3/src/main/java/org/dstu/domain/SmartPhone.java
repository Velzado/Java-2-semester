package org.dstu.domain;

public class SmartPhone extends Phone {

    public SmartPhone(String imei, String model, String phoneNumber, String os, boolean isNfcPresent) {
        super(imei, model, phoneNumber);
        this.os = os;
        this.isNfcPresent = isNfcPresent;
    }
    
    private String os;
    public void setOs(String os) {
        this.os = os;
    }
    public String getOs() {
        return os;
    }

    private boolean isNfcPresent;
    public void setNfcAvailability(boolean isNfcPresent) {
        this.isNfcPresent = isNfcPresent;
    }
    public boolean getNfcAvailability() {
        return isNfcPresent;
    }
}
