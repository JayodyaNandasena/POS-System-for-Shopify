package edu.shopify.dao.custom;

import edu.shopify.dao.CrudDao;
import edu.shopify.dto.Customer;
import edu.shopify.entity.CategoryEntity;
import edu.shopify.entity.CustomerEntity;

public interface CustomerDao extends CrudDao<CustomerEntity> {
    CustomerEntity searchByMobile(String mobile);
    CustomerEntity searchByEmail(String email);
    String getLastId();

    String[] getMobiles();
}
