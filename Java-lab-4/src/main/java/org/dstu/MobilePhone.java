package org.dstu;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "mobilephones")
public class MobilePhone extends Phone implements Serializable, CSVReadable<MobilePhone> {

  @Column(name = "isInfraredPortPresent")
  private boolean isInfraredPortPresent;
  public void setInfraredPortAvailability(boolean isInfraredPortPresent) {
    this.isInfraredPortPresent = isInfraredPortPresent;
  }
  public boolean getInfraredPortAvailability() {
    return isInfraredPortPresent;
  }

  MobilePhone() {
    super();
    isInfraredPortPresent = false;
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
        case "isInfraredPortPresent" -> isInfraredPortPresent = Integer.parseInt(value) == 1;
      }
    }
  }

  public MobilePhone create() {
    return new MobilePhone();
  }

  @Override
  public String toString() {
    return String.format("%7d%20s%20s%15s %b", id, model, imei, phoneNumber, isInfraredPortPresent);
  }
}