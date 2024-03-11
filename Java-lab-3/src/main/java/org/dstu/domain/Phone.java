package org.dstu.domain;

public class Phone {
    public Phone(String imei, String model, String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.model = model;
        this.imei = imei;
    }

    private String phoneNumber;
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    private String model;
    public void setModel(String model) {
        this.model = model;
    }
    public String getModel() {
        return model;
    }

    private String imei;
    public void setIMEI(String imei) {
        this.imei = imei;
    }
    public String getIMEI() {
        return imei;
    }
}
