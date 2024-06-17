package edu.shopify.dao.custom;

import edu.shopify.dao.CrudDao;
import edu.shopify.entity.EmployeeEntity;
import edu.shopify.entity.SupplierEntity;

public interface SupplierDao extends CrudDao<SupplierEntity> {

    Boolean readd(String id);
}
