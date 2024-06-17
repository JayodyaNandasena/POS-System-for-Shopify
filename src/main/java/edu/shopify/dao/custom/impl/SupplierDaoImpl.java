package edu.shopify.dao.custom.impl;

import edu.shopify.dao.custom.SupplierDao;
import edu.shopify.entity.SupplierEntity;
import edu.shopify.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean add(SupplierEntity entity) {
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
    public List<SupplierEntity> getAll() {
        Session session = HibernateUtil.getsession();

        try{
            session.beginTransaction();
            Query<SupplierEntity> query = session.createQuery("from SupplierEntity where isActive = true", SupplierEntity.class);
            List<SupplierEntity> suppliers = query.getResultList();
            session.getTransaction().commit();
            return suppliers;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public SupplierEntity searchById(String id) throws HibernateException {
        Session session = HibernateUtil.getsession();
        session.getTransaction().begin();
        try{
            SupplierEntity supplierEntity = session.get(SupplierEntity.class, id);
            session.getTransaction().commit();
            return supplierEntity;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(SupplierEntity supplierEntity) {
        Session session = HibernateUtil.getsession();
        session.getTransaction().begin();
        session.merge(supplierEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getsession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE SupplierEntity SET isActive = false WHERE id = :id");
        query.setParameter("id", id);
        int rowCount = query.executeUpdate();
        session.getTransaction().commit();
        return rowCount > 0;
    }


    @Override
    public Boolean readd(String id) {
        Session session = HibernateUtil.getsession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE SupplierEntity SET isActive = true WHERE id = :id");
        query.setParameter("id", id);
        int rowCount = query.executeUpdate();
        session.getTransaction().commit();
        return rowCount > 0;
    }

}
