package edu.shopify.dao;

import edu.shopify.dao.custom.impl.CategoryDaoImpl;
import edu.shopify.dao.custom.impl.EmployeeDaoImpl;
import edu.shopify.dao.custom.impl.ProductDaoImpl;
import edu.shopify.dao.custom.impl.SupplierDaoImpl;
import edu.shopify.util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return instance!=null? instance : (instance = new DaoFactory());
    }

    public <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case EMPLOYEE: return (T) new EmployeeDaoImpl();
            case SUPPLIER: return (T) new SupplierDaoImpl();
            case CATEGORY: return (T) new CategoryDaoImpl();
            case PRODUCT: return (T) new ProductDaoImpl();
        }
        return null;
    }
}
