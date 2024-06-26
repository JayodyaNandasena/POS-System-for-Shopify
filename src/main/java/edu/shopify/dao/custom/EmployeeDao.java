package edu.shopify.dao.custom;

import edu.shopify.dao.CrudDao;
import edu.shopify.entity.EmployeeEntity;

public interface EmployeeDao extends CrudDao<EmployeeEntity> {
    Boolean validateLogin(String email, String password);
    Boolean readd(String id);
}
