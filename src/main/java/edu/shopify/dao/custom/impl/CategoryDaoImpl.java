package edu.shopify.dao.custom.impl;

import edu.shopify.dao.custom.CategoryDao;
import edu.shopify.entity.CategoryEntity;
import edu.shopify.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public Boolean add(CategoryEntity entity) {
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
    public List<CategoryEntity> getAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        try{
            Query<CategoryEntity> query = session.createQuery("from CategoryEntity", CategoryEntity.class);
            List<CategoryEntity> categories = query.getResultList();
            session.getTransaction().commit();
            return categories;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            session.close();
        }
    }

    @Override
    public CategoryEntity searchById(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        try{
            CategoryEntity categoryEntity = session.get(CategoryEntity.class, id);
            session.getTransaction().commit();
            return categoryEntity;
        } catch (HibernateException e) {
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public Boolean update(CategoryEntity dto) {
        return false;
    }

    @Override
    public Boolean delete(String id) {
        return false;
    }

}
