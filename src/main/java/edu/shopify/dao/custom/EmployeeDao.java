package edu.shopify.dao.custom;

import edu.shopify.dao.CrudDao;
import edu.shopify.entity.EmployeeEntity;

public interface EmployeeDao extends CrudDao<EmployeeEntity> {
    public Boolean validateLogin(String email, String password);
}
