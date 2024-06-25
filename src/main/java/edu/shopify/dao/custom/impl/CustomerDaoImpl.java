package edu.shopify.dao.custom.impl;

import edu.shopify.dao.custom.CategoryDao;
import edu.shopify.dao.custom.CustomerDao;
import edu.shopify.entity.CategoryEntity;
import edu.shopify.entity.CustomerEntity;
import edu.shopify.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public Boolean add(CustomerEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        try {
            session.persist(entity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public List<CustomerEntity> getAll() {
        return null;
    }

    @Override
    public CustomerEntity searchById(String id) {
        return null;
    }

    @Override
    public Boolean update(CustomerEntity dto) {
        return false;
    }

    @Override
    public Boolean delete(String id) {
        return false;
    }


    @Override
    public CustomerEntity searchByMobile(String mobile) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        try {
            String hql = "FROM CustomerEntity WHERE mobile = :mobile";
            Query<CustomerEntity> query = session.createQuery(hql, CustomerEntity.class);
            query.setParameter("mobile", mobile);
            CustomerEntity customerEntity = query.uniqueResult();
            session.getTransaction().commit();
            return customerEntity;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }


    @Override
    public CustomerEntity searchByEmail(String email) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        try {
            String hql = "FROM CustomerEntity WHERE email = :email";
            Query<CustomerEntity> query = session.createQuery(hql, CustomerEntity.class);
            query.setParameter("email", email);
            CustomerEntity customerEntity = query.uniqueResult();
            session.getTransaction().commit();
            return customerEntity;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }


    @Override
    public String getLastId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        try {
            String hql = "SELECT id FROM CustomerEntity ORDER BY id DESC LIMIT 1";
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);
            String lastCustomerId = query.uniqueResult();
            session.getTransaction().commit();
            return lastCustomerId;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public String[] getMobiles() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        try {
            String hql = "SELECT mobile FROM CustomerEntity";
            Query<String> query = session.createQuery(hql, String.class);
            List<String> mobileList = query.list();
            session.getTransaction().commit();
            return mobileList.toArray(new String[0]);
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return new String[0];
        } finally {
            session.close();
        }
    }


}
