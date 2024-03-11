package org.dstu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "smartphones")
public class SmartPhone extends Phone  implements Serializable, CSVReadable<SmartPhone> {
    SmartPhone() {
        super();
        os = "";
        isNfcPresent = false;
    }

    @Column(name = "os")
    private String os;
    public void setOs(String os) {
        this.os = os;
    }
    public String getOs() {
        return os;
    }

    @Column(name = "isNfcPresent")
    private boolean isNfcPresent;
    public void setNfcAvailability(boolean isNfcPresent) {
        this.isNfcPresent = isNfcPresent;
    }
    public boolean getNfcAvailability() {
        return isNfcPresent;
    }

    public void parseCSVLine(String[] data, String[] titles) {
        for (int i = 0; i < titles.length; i++) {
            final String field = titles[i];
            final String value = data[i];
            switch (field) {
                case "id" -> id = Integer.parseInt(value);
                case "phoneNumber" -> phoneNumber = value;
                case "imei" -> imei = value;
                case "model" -> model = value;
                case "os" -> os = value;
                case "isNfcPresent" -> isNfcPresent = Integer.parseInt(value) == 1;
            }
        }
    }
    public SmartPhone create() {
        return new SmartPhone();
    }

    @Override
    public String toString() {
        return String.format("%7d%20s%20s%15s%20s %b", id, model, imei, phoneNumber, os, isNfcPresent);
    }
}
