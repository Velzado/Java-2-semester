package org.dstu.domain;

public class MobilePhone extends Phone {
    private String property_1;
    private String property_2;
    private String property_3;

    public MobilePhone() {
    }

    public MobilePhone(String[] line) {
        super(line[1], line[2], line[3]);
        property_1 = line[4];
        property_2 = line[5];
        property_3 = line[6];
    }

    public String getProperty_1() {
        return property_1;
    }

    public void setProperty_1(String property_1) {
        this.property_1 = property_1;
    }

    public String getProperty_2() {
        return property_2;
    }

    public void setProperty_2(String property_2) {
        this.property_2 = property_2;
    }

    public String getProperty_3() {
        return property_3;
    }

    public void setProperty_3(String property_3) {
        this.property_3 = property_3;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("MobilePhone: ");
        builder.append(super.toString());
        builder.append(" | Свойство № 1: ");
        builder.append(property_1);
        builder.append(" | Свойство № 2: ");
        builder.append(property_2);
        builder.append(" | Свойство № 3: ");
        builder.append(property_3);
        return builder.toString();
    }
}
