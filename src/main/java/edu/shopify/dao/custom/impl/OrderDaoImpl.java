package edu.shopify.dao.custom.impl;

import edu.shopify.dao.custom.ProductDao;
import edu.shopify.entity.ProductEntity;
import edu.shopify.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDaoImpl implements ProductDao {
    @Override
    public Boolean add(ProductEntity entity) {
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
    public List<ProductEntity> getAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Query<ProductEntity> query = session.createQuery("from ProductEntity where isSelling = true", ProductEntity.class);
        List<ProductEntity> products = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return products;
    }

    @Override
    public ProductEntity searchById(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        try{
            ProductEntity productEntity = session.get(ProductEntity.class, id);
            session.getTransaction().commit();
            return productEntity;
        } catch (HibernateException e) {
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public Boolean update(ProductEntity productEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.merge(productEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE ProductEntity SET isSelling = false WHERE id = :id");
        query.setParameter("id", id);
        int rowCount = query.executeUpdate();
        session.getTransaction().commit();
        return rowCount > 0;
    }


    @Override
    public Boolean readd(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE ProductEntity SET isSelling = true WHERE id = :id");
        query.setParameter("id", id);
        int rowCount = query.executeUpdate();
        session.getTransaction().commit();
        return rowCount > 0;
    }

}
