package edu.shopify.dao.custom;

import edu.shopify.dao.CrudDao;
import edu.shopify.entity.OrderEntity;
import edu.shopify.entity.ProductEntity;

public interface OrderDao extends CrudDao<OrderEntity> {
    Boolean readd(String id);
}
