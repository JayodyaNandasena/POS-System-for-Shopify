package edu.shopify.bo.custom;

import edu.shopify.bo.SuperBo;
import edu.shopify.dto.Customer;
import edu.shopify.dto.Employee;
import javafx.collections.ObservableList;

public interface CustomerBo extends SuperBo {
    Boolean saveCustomer(Customer dto) throws Exception;
    Customer searchCustomerByEmail(String email) throws Exception;
    Customer searchCustomerByMobile(String mobile) throws Exception;
    String getLastId() throws Exception;
    String[] getAllMobiles();
}
