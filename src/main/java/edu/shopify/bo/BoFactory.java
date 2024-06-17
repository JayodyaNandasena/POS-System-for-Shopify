package edu.shopify.bo;

import edu.shopify.bo.custom.impl.EmployeeBoImpl;
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
        }
        return null;
    }

}
