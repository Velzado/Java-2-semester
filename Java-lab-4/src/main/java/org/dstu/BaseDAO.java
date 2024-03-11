package org.dstu;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseDAO {
  private Session session;

  public BaseDAO(Session session) {
    this.session = session;
  }

  public void loadObject(Object target) {
    session.beginTransaction();
    session.persist(target);
    session.getTransaction().commit();
  }

  public void loadObjects(List<Object> targets) {
    session.beginTransaction();
    for (Object target : targets) {
      session.persist(target);
    }

    session.getTransaction().commit();
  }

  public ArrayList<Application> getAllApplications() {
    session.beginTransaction();
    ArrayList<Application> result = (ArrayList<Application>) session.createQuery("FROM org.dstu.Application", Application.class).getResultList();
    session.getTransaction().commit();
    return result;
  }

  public Application getApplicationById(int id) {
    return session.createQuery("FROM Application WHERE id = :id", Application.class)
            .setParameter("id", id)
            .getSingleResult();
  }

  public void updateApplication(Application app) {
    Transaction t = session.beginTransaction();
    session.update(app);
    t.commit();
  }

  public ArrayList<MobilePhone> getMobilePhonesWithApplicationInstalled(Application application) {
    ArrayList<MobilePhone> result = (ArrayList<MobilePhone>) session
        .createQuery("FROM org.dstu.MobilePhone u join fetch u.applications r join fetch r.app a where a.id = :id", MobilePhone.class)
        .setParameter("id", application.getId())
        .getResultList();

    return result;
  }


  public Application findApplicationById(int id) {
    return session.createQuery("FROM org.dstu.Application WHERE id = :id", Application.class)
        .setParameter("id", id)
        .getSingleResult();
  }

  public ArrayList<Object> getAllObjects() {
    ArrayList<Object> result = new ArrayList<>();
    result.addAll(session.createQuery("FROM org.dstu.MobilePhone", MobilePhone.class).getResultList());
    result.addAll(session.createQuery("FROM org.dstu.SmartPhone", SmartPhone.class).getResultList());
    result.addAll(session.createQuery("FROM org.dstu.Application", Application.class).getResultList());

    return result;
  }
}
