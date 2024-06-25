package edu.shopify.bo.custom.impl;

import edu.shopify.bo.custom.CategoryBo;
import edu.shopify.bo.custom.CustomerBo;
import edu.shopify.dao.DaoFactory;
import edu.shopify.dao.custom.CategoryDao;
import edu.shopify.dao.custom.CustomerDao;
import edu.shopify.dto.Category;
import edu.shopify.dto.Customer;
import edu.shopify.entity.CategoryEntity;
import edu.shopify.entity.CustomerEntity;
import edu.shopify.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class CustomerBoImpl implements CustomerBo {
    private final CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);


    @Override
    public Boolean saveCustomer(Customer dto) throws Exception {
        return customerDao.add(new ModelMapper().map(dto, CustomerEntity.class));
    }



    @Override
    public Customer searchCustomerByEmail(String email) throws Exception {
        CustomerEntity customer = customerDao.searchByEmail(email);
        if (customer != null)
            return new ModelMapper().map(customer, Customer.class);
        return null;
    }

    @Override
    public Customer searchCustomerByMobile(String mobile) throws Exception {
        CustomerEntity customer = customerDao.searchByMobile(mobile);
        if (customer != null)
            return new ModelMapper().map(customer, Customer.class);
        return null;
    }

    @Override
    public String getLastId() throws Exception {
        return customerDao.getLastId();
    }

    @Override
    public String[] getAllMobiles() {
        String[] mobiles = customerDao.getMobiles();

        if (mobiles.length == 0){
            return new String[0];
        }
        return mobiles;
    }

}
