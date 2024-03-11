package org.dstu.domain;

public class MobilePhone extends Phone {
    public MobilePhone(String imei, String model, String phoneNumber, String displayType, boolean isInfraredPortPresent) {
        super(imei, model, phoneNumber);
        this.displayType = displayType;
        this.isInfraredPortPresent = isInfraredPortPresent;
    }
    
    private String displayType;
    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }
    public String getDisplayType() {
        return displayType;
    }

    private boolean isInfraredPortPresent;
    public void setInfraredPortAvailability(boolean isInfraredPortPresent) {
        this.isInfraredPortPresent = isInfraredPortPresent;
    }
    public boolean getInfraredPortAvailability() {
        return isInfraredPortPresent;
    }
}
