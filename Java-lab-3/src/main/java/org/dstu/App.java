package org.dstu;

import java.util.List;

import org.dstu.db.DbWorker;
import org.dstu.domain.MobilePhone;
import org.dstu.domain.SmartPhone;
import org.dstu.utils.CsvContent;
import org.dstu.utils.CsvMapper;
import org.dstu.utils.CsvReader;

public class App 
{
    public static void main( String[] args )
    {
        if (args.length < 1) {
            System.out.println("You must specify input file name!");
            return;
        }
        CsvContent csvFileContent = CsvReader.readCsvFile(args[0], ";");
        List<MobilePhone> mobilePhones = CsvMapper.MapMobilePhonesFromRawData(csvFileContent.mobilePhonesRawData);
        List<SmartPhone> smartPhones = CsvMapper.MapSmartPhonesFromRawData(csvFileContent.smartPhonesRawData);

        DbWorker.cleanDatabase();
        DbWorker.insertMobilePhonesRange(mobilePhones);
        DbWorker.insertSmartPhonesRange(smartPhones);

        List<MobilePhone> q1 = DbWorker.mobilePhonesWithInfraredPortQuery();
        System.out.println("\nМобильные телефоны с ИК-портом:");
        for (MobilePhone mp: q1) {
            System.out.println(mp.getModel());
        }

        List<SmartPhone> q2 = DbWorker.smartPhonesWithNFCQuery();
        System.out.println("\nСмартфоны с NFC-датчиком:");
        for (SmartPhone sp: q2) {
            System.out.println(sp.getModel());
        }

        System.out.println("\nDirty read demo");
        DbWorker.dirtyReadDemo();
    }
}
