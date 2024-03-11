package org.dstu;

import jakarta.persistence.*;

import java.util.List;

// Used for csv parsing

@Entity
@Table(name = "phone_application")
public class PhoneApplication implements CSVReadable<PhoneApplication> {
    PhoneApplication() {
        phoneId = 0;
        appId = 0;
    }

    public int getPhoneId() {
        return phoneId;
    }
    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    @Id
    @Column(name = "phone_id")
    private int phoneId;

    public int getAppId() {
        return appId;
    }
    public void setAppId(int appId) {
        this.appId = appId;
    }

    @Id
    @Column(name = "app_id")
    private int appId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("phoneId")
    private Phone phone;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("appId")
    private Application app;


    public void parseCSVLine(String[] data, String[] titles) {
        for (int i = 0; i < titles.length; i++) {
            final String field = titles[i];
            final String value = data[i];
            switch (field) {
                case "phone" -> phoneId = Integer.parseInt(value);
                case "application" -> appId = Integer.parseInt(value);
            }
        }
    }


    public PhoneApplication create() {
        return new PhoneApplication();
    }
}
