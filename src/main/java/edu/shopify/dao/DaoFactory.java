package edu.shopify.dao;

import edu.shopify.dao.custom.impl.*;
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
            case CUSTOMER: return (T) new CustomerDaoImpl();
            case ORDER: return (T) new OrderDaoImpl();
            case ORDERPRODUCT: return (T) new OrderProductDaoImpl();
        }
        return null;
    }
}
