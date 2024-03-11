package org.dstu;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args) throws IOException {
        SessionFactory sessionFactory = new Configuration().configure()
                .addAnnotatedClass(Application.class)
                .addAnnotatedClass(Phone.class)
                .addAnnotatedClass(MobilePhone.class)
                .addAnnotatedClass(SmartPhone.class)
                .addAnnotatedClass(PhoneApplication.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        BaseDAO worker = new BaseDAO(session);

        ArrayList<Object> data = new ArrayList<>();
        data.addAll(new CSVDataReader<>("applications.csv", ",", new Application()).readFile());
        data.addAll(new CSVDataReader<>("smartphones.csv", ",", new SmartPhone()).readFile());
        data.addAll(new CSVDataReader<>("mobilephones.csv", ",", new MobilePhone()).readFile());
        data.addAll(new CSVDataReader<>("appsInstalled.csv", ",", new PhoneApplication()).readFile());

        worker.loadObjects(data);

        System.out.println("Все объекты в базе данных:\n");
        worker.getAllObjects().forEach(item -> {
            System.out.println(item);
        });
        System.out.println();

        System.out.println("Все приложения:");
        for (Application application : worker.getAllApplications()) {
            System.out.println(application);
        }
        System.out.println();

        // Изменение Application
        Application app = worker.getApplicationById(7);
        System.out.println("Приложение до изменения:");
        System.out.println(app);
        app.setVersion("2.0.1");
        app.setTitle("Space Imposter");
        worker.updateApplication(app);
        Application updatedApp = worker.getApplicationById(7);
        System.out.println("Приложение после изменения:");
        System.out.println(updatedApp);
        System.out.println();

        System.out.println("Все мобильные телефоны с установленным приложением Space Impact:");
        worker.getMobilePhonesWithApplicationInstalled(new Application(7))
                .forEach(mobilePhone -> System.out.println(mobilePhone));
        System.out.println();

        session.close();
    }
}
