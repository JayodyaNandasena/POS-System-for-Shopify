package edu.shopify.dao.custom;

import edu.shopify.dao.CrudDao;
import edu.shopify.entity.EmployeeEntity;
import edu.shopify.entity.ProductEntity;

public interface ProductDao extends CrudDao<ProductEntity> {
    Boolean readd(String id);
}
