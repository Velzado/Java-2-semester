package org.dstu;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "phones")
@Inheritance(strategy=InheritanceType.JOINED)
public class Phone {
    Phone() {
        id = 0;
        phoneNumber = "";
        model = "";
        imei = "";
    }

    @Id
    @Column(name = "id")
    protected int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "phoneNumber")
    protected String phoneNumber;
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Column(name = "model")
    protected String model;
    public void setModel(String model) {
        this.model = model;
    }
    public String getModel() {
        return model;
    }

    @Column(name = "imei")
    protected String imei;
    public void setIMEI(String imei) {
        this.imei = imei;
    }
    public String getIMEI() {
        return imei;
    }

    @OneToMany(mappedBy = "phone",
            fetch = FetchType.LAZY)
    private List<PhoneApplication> applications;
}
