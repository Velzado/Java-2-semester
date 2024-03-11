package org.dstu.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.dstu.domain.MobilePhone;
import org.dstu.domain.SmartPhone;

public class DbWorker {
    public static void cleanDatabase() {
        Connection conn = DbConnection.getConnection();
        try {
            Statement cleaner = conn.createStatement();
            cleaner.executeUpdate("DELETE FROM mobile_phones");
            cleaner.executeUpdate("DELETE FROM smart_phones");
            cleaner.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void insertMobilePhonesRange(List<MobilePhone> mobilePhones) {
        Connection conn = DbConnection.getConnection();
        try {
            PreparedStatement mobilePhonesSt = conn.prepareStatement(
                    "INSERT INTO mobile_phones (imei, model, phone_number, display_type, is_infrared_port) " +
                            "VALUES (?, ?, ?, ?, ?)");
            for (MobilePhone mobilePhone: mobilePhones) {
                mobilePhonesSt.setString(1, mobilePhone.getIMEI());
                mobilePhonesSt.setString(2, mobilePhone.getModel());
                mobilePhonesSt.setString(3, mobilePhone.getPhoneNumber());
                mobilePhonesSt.setString(4, mobilePhone.getDisplayType());
                mobilePhonesSt.setBoolean(5, mobilePhone.getInfraredPortAvailability());
                mobilePhonesSt.addBatch();
            }
            int[] stRes =  mobilePhonesSt.executeBatch();
            for (int num: stRes) {
                System.out.printf("Успешно добавлена запись: %b\n", num);
            }

            mobilePhonesSt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void insertSmartPhonesRange(List<SmartPhone> smartPhones) {
        Connection conn = DbConnection.getConnection();
        try {
            PreparedStatement smartPhonesSt = conn.prepareStatement(
                    "INSERT INTO smart_phones (imei, model, phone_number, os, is_nfc) " +
                            "VALUES (?, ?, ?, ?, ?)");
            for (SmartPhone smartPhone: smartPhones) {
                smartPhonesSt.setString(1, smartPhone.getIMEI());
                smartPhonesSt.setString(2, smartPhone.getModel());
                smartPhonesSt.setString(3, smartPhone.getPhoneNumber());
                smartPhonesSt.setString(4, smartPhone.getOs());
                smartPhonesSt.setBoolean(5, smartPhone.getNfcAvailability());
                smartPhonesSt.addBatch();
            }
            int[] stRes =  smartPhonesSt.executeBatch();
            for (int num: stRes) {
                System.out.printf("Успешно добавлена запись: %b\n", num);
            }

            smartPhonesSt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<SmartPhone> smartPhonesWithNFCQuery() {
        Connection conn = DbConnection.getConnection();
        List<SmartPhone> res = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM smart_phones WHERE is_nfc = true");
            while (rs.next()) {
                String imei = rs.getString("imei");
                String model = rs.getString("model");
                String phoneNumber = rs.getString("phone_number");
                String os = rs.getString("os");
                boolean isNfcPresent = rs.getBoolean("is_nfc");
                res.add(new SmartPhone(phoneNumber, model, imei, os, isNfcPresent));
            }
            rs.close();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    public static List<MobilePhone> mobilePhonesWithInfraredPortQuery() {
        Connection conn = DbConnection.getConnection();
        List<MobilePhone> res = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM mobile_phones WHERE is_infrared_port = true");
            while (rs.next()) {
                String imei = rs.getString("imei");
                String model = rs.getString("model");
                String phoneNumber = rs.getString("phone_number");
                String displayType = rs.getString("display_type");
                boolean isInfraredPortPresent = rs.getBoolean("is_infrared_port");
                res.add(new MobilePhone(phoneNumber, model, imei, displayType, isInfraredPortPresent));
            }
            rs.close();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    public static void dirtyReadDemo() {
        Runnable first = () -> {
            Connection conn1 = DbConnection.getNewConnection();
            if (conn1 != null) {
                try {
                    conn1.setAutoCommit(false);
                    conn1.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                    Statement upd = conn1.createStatement();
                    upd.executeUpdate("UPDATE mobile_phones SET phone_number='1111111111' WHERE phone_number='88005553535'");
                    Thread.sleep(2000);
                    conn1.rollback();
                    upd.close();
                    Statement st = conn1.createStatement();
                    System.out.println("In the first thread:");
                    ResultSet rs = st.executeQuery("SELECT * FROM mobile_phones");
                    while (rs.next()) {
                        System.out.println(rs.getString("phone_number"));
                    }
                    st.close();
                    rs.close();
                    conn1.close();
                } catch (SQLException | InterruptedException throwables) {
                    throwables.printStackTrace();
                }
            }
        };

        Runnable second = () -> {
            Connection conn2 = DbConnection.getNewConnection();
            if (conn2 != null) {
                try {
                    Thread.sleep(500);
                    conn2.setAutoCommit(false);
                    conn2.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                    Statement st = conn2.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM mobile_phones");
                    while (rs.next()) {
                        System.out.println(rs.getString("phone_number"));
                    }
                    rs.close();
                    st.close();
                    conn2.close();
                } catch (SQLException | InterruptedException throwables) {
                    throwables.printStackTrace();
                }
            }
        };
        Thread th1 = new Thread(first);
        Thread th2 = new Thread(second);
        th1.start();
        th2.start();
    }
}
