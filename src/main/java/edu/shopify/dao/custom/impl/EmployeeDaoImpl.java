package edu.shopify.dao.custom.impl;

import edu.shopify.dao.custom.EmployeeDao;
import edu.shopify.entity.EmployeeEntity;
import edu.shopify.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean add(EmployeeEntity entity) {
        Session session = HibernateUtil.getsession();
        session.getTransaction().begin();

        try {
            session.persist(entity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public List<EmployeeEntity> getAll() {
        Session session = HibernateUtil.getsession();
        session.beginTransaction();
        Query<EmployeeEntity> query = session.createQuery("from EmployeeEntity where isActive = true", EmployeeEntity.class);
        List<EmployeeEntity> employees = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return employees;
    }

    @Override
    public EmployeeEntity searchById(String id) {
        Session session = HibernateUtil.getsession();
        session.getTransaction().begin();
        try{
            EmployeeEntity employeeEntity = session.get(EmployeeEntity.class, id);
            session.getTransaction().commit();
            return employeeEntity;
        } catch (HibernateException e) {
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(EmployeeEntity employeeEntity) {
        Session session = HibernateUtil.getsession();
        session.getTransaction().begin();
        session.merge(employeeEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getsession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE EmployeeEntity SET isActive = false WHERE id = :id");
        query.setParameter("id", id);
        int rowCount = query.executeUpdate();
        session.getTransaction().commit();
        return rowCount > 0;
    }

    @Override
    public Boolean validateLogin(String email, String password) {
        Session session = HibernateUtil.getsession();
        session.getTransaction().begin();

        Query<EmployeeEntity> query = session.createQuery(
                "FROM EmployeeEntity WHERE email = :email AND password = :password", EmployeeEntity.class);
        query.setParameter("email", email);
        query.setParameter("password", password);

        EmployeeEntity employee = query.uniqueResult();

        session.getTransaction().commit();
        session.close();

        return employee != null;
    }

}
