package edu.shopify.dao.custom.impl;

import edu.shopify.dao.custom.SupplierDao;
import edu.shopify.entity.SupplierEntity;
import edu.shopify.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public Boolean add(SupplierEntity entity) {
        Session session = HibernateUtil.getSession();
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
        Session session = HibernateUtil.getSession();

        try{
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<SupplierEntity> criteriaQuery = criteriaBuilder.createQuery(SupplierEntity.class);
            Root<SupplierEntity> root = criteriaQuery.from(SupplierEntity.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get("isActive"), true));
            List<SupplierEntity> suppliers = session.createQuery(criteriaQuery).getResultList();
            session.getTransaction().commit();
            return suppliers;
//            session.beginTransaction();
//            Query<SupplierEntity> query = session.createQuery("from supplierentity where isActive = true", SupplierEntity.class);
//            List<SupplierEntity> suppliers = query.getResultList();
//            session.getTransaction().commit();
//            return suppliers;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public SupplierEntity searchById(String id) throws HibernateException {
        Session session = HibernateUtil.getSession();
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
    public Boolean update(SupplierEntity supplierEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.merge(supplierEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE SupplierEntity SET isActive = false WHERE id = :id");
        query.setParameter("id", id);
        int rowCount = query.executeUpdate();
        session.getTransaction().commit();
        return rowCount > 0;
    }


    @Override
    public Boolean readd(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE SupplierEntity SET isActive = true WHERE id = :id");
        query.setParameter("id", id);
        int rowCount = query.executeUpdate();
        session.getTransaction().commit();
        return rowCount > 0;
    }

}
