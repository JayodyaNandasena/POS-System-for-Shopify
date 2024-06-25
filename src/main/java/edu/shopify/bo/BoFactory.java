package edu.shopify.bo;

import edu.shopify.bo.custom.impl.*;
import edu.shopify.util.BoType;

public class BoFactory {
    private static BoFactory instance;
    private BoFactory(){}

    public static BoFactory getInstance() {
        return instance!= null ? instance: (instance=new BoFactory());
    }


    public <T extends SuperBo>T getBo(BoType type) throws Exception {
        switch(type){
            case EMPLOYEE: return (T) new EmployeeBoImpl();
            case SUPPLIER: return (T) new SupplierBoImpl();
            case CATEGORY: return (T) new CategoryBoImpl();
            case PRODUCT: return (T) new ProductBoImpl();
            case CUSTOMER: return (T)new CustomerBoImpl();
            case ORDER: return (T)new OrderBoImpl();
            case ORDERPRODUCT: return (T)new OrderProductBoImpl();
        }
        return null;
    }

}
