package org.dstu.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReader {
    public static CsvContent readCsvFile (String fileName, String regex) {
        System.out.printf("Считывание csv-файла: %s\n", fileName);
        CsvContent content = new CsvContent();
        File file = new File(fileName);
        try {
            InputStreamReader streamReader = new InputStreamReader(new FileInputStream(file), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String fileLine;
            while ((fileLine = bufferedReader.readLine()) != null) {
                System.out.println(fileLine);
                List<String> splitString = new ArrayList<>(Arrays.asList(fileLine.split(regex)));
                if (splitString.get(0).equals("0")) {
                    splitString.remove(0);
                    content.mobilePhonesRawData.add(splitString);
                } else if (splitString.get(0).equals("1")) {
                    splitString.remove(0);
                    content.smartPhonesRawData.add(splitString);
                }
            }
            System.out.println();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
