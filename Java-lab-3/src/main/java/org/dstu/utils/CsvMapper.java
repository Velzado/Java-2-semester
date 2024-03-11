package org.dstu.utils;

import java.util.ArrayList;
import java.util.List;

import org.dstu.domain.MobilePhone;
import org.dstu.domain.SmartPhone;

public class CsvMapper {
    public static List<MobilePhone> MapMobilePhonesFromRawData(List<List<String>> data) {
        List<MobilePhone> mobilePhones = new ArrayList<MobilePhone>();
        for (List<String> el: data) {
            MobilePhone mobilePhone = new MobilePhone(
                el.get(0), 
                el.get(1), 
                el.get(2), 
                el.get(3), 
                Boolean.parseBoolean(el.get(4))
            );
            mobilePhones.add(mobilePhone);
        }

        return mobilePhones;
    }

    public static List<SmartPhone> MapSmartPhonesFromRawData(List<List<String>> data) {
        List<SmartPhone> smartPhones = new ArrayList<SmartPhone>();
        for (List<String> el: data) {
            SmartPhone smartPhone = new SmartPhone(
                el.get(0), 
                el.get(1), 
                el.get(2), 
                el.get(3), 
                Boolean.parseBoolean(el.get(4))
            );
            smartPhones.add(smartPhone);
        }

        return smartPhones;
    }
}
